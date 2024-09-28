import React from "react";
import MPAccordion from "./MPAccordion";
import { Typography } from "@mui/material";

const MostPressing = ({
  results,
  categories,
}: {
  results: any;
  categories: string[];
}) => {
  return (
    <>
      <Typography variant="h6" gutterBottom>
        Viktigste tiltak
      </Typography>
      {categories.map((item, index) => (
        <MPAccordion
          key={index}
          header={item}
          results={results.categories[item]}
        />
      ))}
    </>
  );
};

export default MostPressing;
