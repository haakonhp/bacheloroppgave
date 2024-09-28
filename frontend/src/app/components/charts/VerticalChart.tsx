import React, { useRef } from "react";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const VerticalChart = ({
  results,
  categories,
  setSecondBarImage,
  visible,
}: {
  results: any;
  categories: string[];
  setSecondBarImage: React.Dispatch<React.SetStateAction<string | undefined>>;
  visible: boolean;
}) => {
  const chartRef = useRef<ChartJS>(null);

  const options = {
    animation: {
      onComplete: function () {
        setSecondBarImage(chartRef.current?.toBase64Image());
      },
    },
    responsive: true,
    plugins: {
      legend: {
        position: "top" as const,
      },
      title: {
        display: true,
        text: "Fullførte kontroller",
      },
    },
  };

  const data = {
    labels: categories,
    datasets: [
      {
        label: "Fullført",
        data: categories.map(
          (category, _) => results.categories[category].satisfactoryElements
        ),
        backgroundColor: "rgba(53, 162, 235, 0.5)",
      },
      {
        label: "Ikke fullført",
        data: categories.map(
          (category, _) => results.categories[category].unsatisfactoryElements
        ),
        backgroundColor: "rgba(255, 99, 132, 0.5)",
      },
    ],
  };
  return (
    <Bar
      options={options}
      data={data}
      // @ts-ignore
      ref={chartRef}
      style={{ display: visible ? "block" : "none" }}
    />
  );
};

export default VerticalChart;
