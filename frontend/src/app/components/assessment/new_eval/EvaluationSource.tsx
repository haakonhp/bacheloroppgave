"use client";
import { Box, Checkbox, FormControlLabel, FormGroup } from "@mui/material";
import React, { useEffect, useState } from "react";

const EvaluationSource = ({
  setSelectedSources,
}: {
  setSelectedSources: React.Dispatch<React.SetStateAction<string[]>>;
}) => {
  const [sources, setSources] = useState<string[] | null>(null);

  useEffect(() => {
    const fetchUniqueSources = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/controls/sources/distinct",
          {
            method: "GET",
          }
        );
        const result = await response.json();
        if (Array.isArray(result)) {
          setSources(result);
        } else {
          console.error("API response is not an array:", result);
        }
      } catch (error) {
        console.error("Error fetching company:", error);
      }
    };

    fetchUniqueSources();
  }, []);

  const handleCheckboxChange =
    (source: string) => (event: React.ChangeEvent<HTMLInputElement>) => {
      const isChecked = event.target.checked;
      if (isChecked) {
        setSelectedSources((prevSelected) => [...prevSelected, source]);
      } else {
        setSelectedSources((prevSelected) =>
          prevSelected.filter((item) => item !== source)
        );
      }
    };

  if (sources === null) {
    return <p>Laster...</p>;
  } else if (sources.length === 0) {
    return <p> Fant ingen kilder</p>;
  } else {
    return (
      <>
        <Box sx={{ height: 300, overflow: "hidden", overflowY: "scroll" }}>
          <FormGroup>
            {sources.map((item, index) => (
              <FormControlLabel
                key={index}
                control={<Checkbox onChange={handleCheckboxChange(item)} />}
                label={item}
              />
            ))}
          </FormGroup>
        </Box>
      </>
    );
  }
};

export default EvaluationSource;
