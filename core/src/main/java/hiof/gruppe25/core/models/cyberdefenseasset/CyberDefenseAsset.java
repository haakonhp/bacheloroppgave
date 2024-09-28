package hiof.gruppe25.core.models.cyberdefenseasset;

public class CyberDefenseAsset {
    private String defenseAsset;

    protected CyberDefenseAsset() {
    }

    protected CyberDefenseAsset(CyberDefenseAssetBuilder builder) {
        this.defenseAsset = builder.defenseAsset;
    }

    public String getDefenseAsset() {
        return defenseAsset;
    }

    protected void setDefenseAsset(String defenseAsset) {
        this.defenseAsset = defenseAsset;
    }
}
