import AssessmentType from "@/types/AssessmentType";
import { useState, useEffect } from "react";

const useFetchCompanyEvalResults = async (assessmentId: string) => {
  const [combinedResult, setCombinedResult] = useState<any>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const responseInfo = await fetch(
          `http://localhost:8080/evaluations/${assessmentId}`
        );
        if (!responseInfo.ok) {
          throw new Error("Failed to fetch assessment info");
        }
        const assessmentInfo = (await responseInfo.json()) as AssessmentType;

        const responseCorp = await fetch(
          `http://localhost:8080/corporations/${assessmentInfo.corporation}`
        );
        if (!responseCorp.ok) {
          throw new Error("Failed to fetch corporation info");
        }
        const corporationResult = await responseCorp.json();

        const responseResults = await fetch(
          `http://localhost:8080/evaluations/results/${assessmentId}`
        );
        if (!responseResults.ok) {
          throw new Error("Failed to fetch results");
        }
        const assessmentResult = await responseResults.json();

        const combinedResult = {
          ...assessmentResult,
          assessmentInfo,
          corporationResult,
        };

        setCombinedResult(combinedResult);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [assessmentId]);

  return combinedResult;
};

export default useFetchCompanyEvalResults;
