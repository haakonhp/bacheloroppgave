import ControlType from "./ControlType";

export default interface AssessmentType {
  id: string;
  name: string;
  createdAt: string;
  updatedAt: string;
  corporation: string;
  createdByTemplate: number;
  answers: Answer[];
  answerCount: number;
  completedAnswerCount: number;
}

export interface Answer {
  id: string;
  control: ControlType;
  lastUpdatedAt: string;
  value: number;
  desiredValue: number;
}

export interface NewAnswer {
  id: string;
  lastUpdatedAt: string;
  value: number;
  desiredValue: number;
}
