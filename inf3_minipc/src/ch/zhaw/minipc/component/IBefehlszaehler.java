package ch.zhaw.minipc.component;

public interface IBefehlszaehler {
	
	//Der Befehlszaehler zeigt auf die Position im Speicher in der der n�chste
	//Befehl steht.
	
	/**
	 * Setzt den Befehlsz�hler auf das n�chste Feld im memory
	 * Das bedeutet er erh�ht sich um 1
	 */
	void incrementBefehlszaehler();

	void decrementBefehlszaehler(); //bin nicht sicher ob es das braucht
	/**
	 * Setzt den Befehlsz�hler auf eine neue Position
	 * @param newPosition 
	 */
	void jumpToPosition(int newPosition);
	
	/**
	 * Gibt die aktuelle Position des Befehlsz�hlers zur�ck
	 * @return aktuelle Position
	 */
	int getPosition();
}
