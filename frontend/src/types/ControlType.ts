interface ControlType {
  controlNumber: number;
  details: string;
  controlID: string;
  controlOverview: string;
  controlQuestion: string;
  priority: number;
  source: string;
  maturityLevel0: string;
  maturityLevel1: string;
  maturityLevel2: string;
  maturityLevel3: string;
  maturityLevel4: string;
  maturityLevel5: string;
  cyberDefenseFunction: string;
  cyberDefenseFunctionColor: string;
  cyberDefenseAssetColor: string;
  controlTypes: string[];
  cyberDefenseAsset: string[];
  implementingFrameworks: ImplementingFramework[];
}

interface ImplementingFramework {
  frameworkReferenceListId: string;
  dbReferences: string[];
  framework: Framework;
}

interface Framework {
  frameworkNumber: number;
  framework: string;
}

export default ControlType;
