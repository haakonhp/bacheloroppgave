import React, { useRef } from "react";
import {
  Chart as ChartJS,
  RadialLinearScale,
  PointElement,
  LineElement,
  Filler,
  Tooltip,
  Legend,
} from "chart.js";
import { Radar } from "react-chartjs-2";

ChartJS.register(
  RadialLinearScale,
  PointElement,
  LineElement,
  Filler,
  Tooltip,
  Legend
);

const SpiderChart = ({
  results,
  categories,
  setSpiderImage,
  visible,
}: {
  results: any;
  categories: string[];
  setSpiderImage: React.Dispatch<React.SetStateAction<string | undefined>>;
  visible: boolean;
}) => {
  const chartRef = useRef<ChartJS>(null);

  const options = {
    layout: {
      padding: 0,
    },
    animation: {
      onComplete: function () {
        setSpiderImage(chartRef.current?.toBase64Image());
      },
    },
    spanGaps: true,
    responsive: true,
    scales: {
      r: {
        min: 0,
        beginAtZero: true,
        max: 5,
        steps: 1,
      },
    },
  };

  const data = {
    labels: categories,
    datasets: [
      {
        label: "Oppnådd poengsum",
        data: categories.map(
          (category, _) => results.categories[category].rawGapScoreAverage
        ),
        backgroundColor: "rgba(102, 204, 153, 0.4)",
        borderColor: "rgba(27, 163, 156)",
        borderWidth: 1,
      },
      {
        label: "Ønsket poengsum",
        data: categories.map(
          (category, _) => results.categories[category].desiredGapScoreAverage
        ),

        backgroundColor: "rgba(53, 162, 235, 0.4)",
        borderColor: "rgb(30,144,255)",
        borderWidth: 1,
      },
    ],
  };
  return (
    <Radar
      options={options}
      data={data}
      // @ts-ignore
      ref={chartRef}
      style={{ display: visible ? "block" : "none" }}
    ></Radar>
  );
};

export default SpiderChart;
