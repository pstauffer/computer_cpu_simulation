package ch.zhaw.minipc.io;

import java.util.List;

public interface ICodeReader {
	/**
	 * Diese Methode liest den Programmcode den der Power PC emulator
	 * ausführen soll aus einem File und schreibt jeden Befehl einzeln
	 * in die Liste
	 * @param path Pfad des zu lesenden Files
	 * @return Liste mit allen gelesenen Befehlen
	 */
	List<String> readParameterFromFile(String path);
	List<String> readCodeFromFile(String path);
}
