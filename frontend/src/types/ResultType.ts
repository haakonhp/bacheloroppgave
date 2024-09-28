import ControlType from "./ControlType";

export type ResultType = {
  evaluationId: string;
  categories: {
    [categoryName: string]: CategoryData;
  };
};

type CategoryData = {
  mostPressing: ControlType[];
  scoreAverage: number;
  unweightedGapScoreAverage: number;
  unweightedPenaltyAverage: number;
  weightedGapScoreAverage: number;
  weightedPenaltyAverage: number;
};
