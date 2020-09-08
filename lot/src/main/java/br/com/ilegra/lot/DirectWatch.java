package br.com.ilegra.lot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import br.com.ilegra.lot.service.RegisterServiceImpl;

public class DirectWatch {

	public static void main(String[] args) throws IOException, InterruptedException {

		RegisterServiceImpl service = new RegisterServiceImpl();

		WatchService watchService = null;
		try {
			watchService = FileSystems.getDefault().newWatchService();
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		Path inputPath = Paths.get(System.getProperty("user.home").concat(File.separator).concat("data")
				.concat(File.separator).concat("in"));
		Path outputPath = Paths.get(System.getProperty("user.home").concat(File.separator).concat("data")
				.concat(File.separator).concat("out"));

		try {
			inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		WatchKey key;

		try {
			while ((key = watchService.take()) != null) {
				for (WatchEvent<?> watchEvent : key.pollEvents()) {

					String filename = watchEvent.context().toString();

					if (".txt".equalsIgnoreCase(filename.substring(filename.length() - 4))) {

						Path inputFilePath = inputPath.resolve((Path) watchEvent.context());
						Path outputFilePath = outputPath.resolve(filename.replace(".txt", ".out.txt"));

						System.out.println("Lendo arquivo de ".concat(inputFilePath.toString()));
						List<String> lines = null;
						try {
							lines = Files.readAllLines(inputFilePath);
							System.out.println(lines);

							System.out.println(lines.size());

						} catch (IOException e1) {
							e1.printStackTrace();
						}

						System.out.println("processando...");

						service.registerAll(lines);
						try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {
							writer.write(service.getReportResult());
							System.out.println("Resultado do processamento em ".concat(outputFilePath.toString()));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				key.reset();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}