package hiof.gruppe25.core.models.cyberdefenseasset;

public class CyberDefenseAssetBuilder {
    protected String defenseAsset;

    public CyberDefenseAssetBuilder(String defenseAsset) {
        this.defenseAsset = defenseAsset;
    }

    public void setDefenseAsset(String defenseAsset) {
        this.defenseAsset = defenseAsset;
    }

    public CyberDefenseAsset build() {
        return new CyberDefenseAsset(this);
    }

}
