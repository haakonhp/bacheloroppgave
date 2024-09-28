import { Checkbox, FormControlLabel, FormGroup } from "@mui/material";
import React, { useEffect } from "react";

const EvaluationFrameworks = ({
  sourceFrameworks,
  setSourceFrameworks,
}: {
  sourceFrameworks: [] | string[];
  setSourceFrameworks: React.Dispatch<React.SetStateAction<[] | string[]>>;
}) => {
  useEffect(() => {
    fetchUniqueSources();
  }, []);

  const fetchUniqueSources = async () => {
    try {
      const response = await fetch(`http://localhost:8080/newEvaluation}`);
      const result = await response.json();
      setSourceFrameworks(result);
    } catch (error) {
      console.error("Error fetching company:", error);
    }
  };

  if (sourceFrameworks.length === 0) {
    return <p>Laster...</p>;
  } else {
    return (
      <>
        <FormGroup>
          {/* https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set/forEach */}
          {Array.from(sourceFrameworks).map((item, index) => (
            <FormControlLabel
              key={index}
              control={<Checkbox />}
              label={item as string}
            />
          ))}
        </FormGroup>
      </>
    );
  }
};

export default EvaluationFrameworks;
