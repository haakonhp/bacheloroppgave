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

const MaturityChart = ({
  results,
  categories,
  setBarImage,
  visible,
}: {
  results: any;
  categories: string[];
  setBarImage: React.Dispatch<React.SetStateAction<string | undefined>>;
  visible: boolean;
}) => {
  const chartRef = useRef<ChartJS>(null);
  const options = {
    animation: {
      onComplete: function () {
        setBarImage(chartRef.current?.toBase64Image());
      },
    },
    scales: {
      y: {
        min: 0,
      },
    },
    responsive: true,
    plugins: {
      legend: {
        position: "top" as const,
      },
      title: {
        display: true,
        text: "Avstand fra ønsket tilstand",
      },
    },
  };

  const colors = [
    "rgba(252, 214, 112, 1)",
    "rgba(249, 180, 45, 1)",
    "rgba(255, 148, 112, 1)",
    "rgba(242, 120, 75, 1)",
    "rgba(211, 84, 0, 1)",
  ];

  const data = {
    labels: categories,
    datasets: Object.keys(
      results.categories[categories[0]].resultsByDifference
    ).map((difference, index) => ({
      label: (index + 1).toString(),
      data: categories.map(
        (category, index) =>
          results.categories[category].resultsByDifference[difference]
      ),
      backgroundColor: colors[index],
    })),
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

export default MaturityChart;
