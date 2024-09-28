package hiof.gruppe25.core.persistenceinterfaces;

public interface iBackupManager {
    public byte[] createBackup();
    public boolean applyBackup(byte[] toWrite);
}
