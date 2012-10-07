package ch.zhaw.minipc.component;

public interface IBefehlszaehler {
	
	//Der Befehlszaehler zeigt auf die Position im Speicher in der der nächste
	//Befehl steht.
	
	/**
	 * Setzt den Befehlszähler auf das nächste Feld im memory
	 * Das bedeutet er erhöht sich um 1
	 */
	void incrementBefehlszaehler();

	void decrementBefehlszaehler(); //bin nicht sicher ob es das braucht
	/**
	 * Setzt den Befehlszähler auf eine neue Position
	 * @param newPosition 
	 */
	void jumpToPosition(int newPosition);
	
	/**
	 * Gibt die aktuelle Position des Befehlszählers zurück
	 * @return aktuelle Position
	 */
	int getPosition();
}
