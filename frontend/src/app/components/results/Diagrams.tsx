import React from "react";
import SpiderChart from "../charts/SpiderChart";
import VerticalChart from "../charts/VerticalChart";
import MaturityChart from "../charts/MaturityChart";

const Diagrams = ({
  filter,
  results,
  categories,
  setSpiderImage,
  setBarImage,
  setSecondBarImage,
}: {
  filter: string[];
  results: any;
  categories: string[];
  setSpiderImage: React.Dispatch<React.SetStateAction<string | undefined>>;
  setBarImage: React.Dispatch<React.SetStateAction<string | undefined>>;
  setSecondBarImage: React.Dispatch<React.SetStateAction<string | undefined>>;
}) => {
  return (
    <>
      {
        <SpiderChart
          results={results}
          categories={categories}
          setSpiderImage={setSpiderImage}
          visible={filter.includes("Modenhetsnivå spider")}
        />
      }

      {
        <MaturityChart
          results={results}
          categories={categories}
          setBarImage={setBarImage}
          visible={filter.includes("Avstand fra ønsket tilstand")}
        />
      }
      {
        <VerticalChart
          results={results}
          categories={categories}
          setSecondBarImage={setSecondBarImage}
          visible={filter.includes("Fullførte mål søylediagram")}
        />
      }
    </>
  );
};

export default Diagrams;
