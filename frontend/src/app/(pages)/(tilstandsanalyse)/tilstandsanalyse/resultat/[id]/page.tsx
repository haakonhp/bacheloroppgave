"use client";
import React, { useEffect, useState } from "react";

import {
  Button,
  ButtonGroup,
  Checkbox,
  Container,
  Divider,
  FormControlLabel,
  FormGroup,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import Loading from "@/app/components/shared/Loading";
import { ResultType } from "@/types/ResultType";
import MostPressing from "@/app/components/results/MostPressing";
import Diagrams from "@/app/components/results/Diagrams";
import { DownloadPDF } from "@/app/components/createPDF/PDFCreate";
import AssessmentType from "@/types/AssessmentType";
import { CompanyType } from "@/types/CompanyType";

const App = ({ params }: { params: { id: string } }) => {
  const assessmentId = params.id;

  const [results, setResults] = useState<ResultType | null>(null);
  const [assessment, setAssessment] = useState<AssessmentType | null>(null);

  const [company, setCompany] = useState<CompanyType | null>(null);
  const [mpAmount, setMpAmount] = useState<number>(5);

  const [filter, setFilter] = useState<string[]>([]);
  const [spiderImage, setSpiderImage] = useState<string | undefined>(undefined);
  const [barImage, setBarImage] = useState<string | undefined>(undefined);
  const [secondBarImage, setSecondBarImage] = useState<string | undefined>(
    undefined
  );

  const handleCheckboxSelection = (value: string) => {
    if (filter.includes(value)) {
      setFilter(filter.filter((item) => item !== value));
    } else {
      setFilter([...filter, value]);
    }
  };

  useEffect(() => {
    fetchResults();
  }, [mpAmount]);

  useEffect(() => {
    fetchResults();
    fetchEvaluation();
  }, []);

  const fetchResults = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/evaluations/results/${assessmentId}?mostPressing=${mpAmount}`
      );
      if (!response.ok) {
        throw new Error("Failed to fetch data");
      }
      const result = await response.json();
      setResults(result);
    } catch (error) {
      console.error("Error fetching results:", error);
    }
  };

  const fetchEvaluation = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/evaluations/${assessmentId}`
      );
      const result = (await response.json()) as AssessmentType;
      setAssessment(result);
      fetchCompany(result.corporation);
    } catch (error) {
      console.error("Error fetching company:", error);
    }
  };

  const fetchCompany = async (corporationId: string) => {
    try {
      const response = await fetch(
        `http://localhost:8080/corporations/${corporationId}`
      );
      const result = (await response.json()) as CompanyType;
      setCompany(result);
    } catch (error) {
      console.error("Error fetching company:", error);
    }
  };

  if (results === null || company === null) {
    return <Loading />;
  }

  const unSortedCategories = Object.keys(results.categories);
  const categories = unSortedCategories.sort();
  const categoriesAmount = categories.length;

  return (
    <main className="flex flex-col items-center min-h-screen p-4 sm:p-8">
      <Container maxWidth="md">
        <Typography variant="h4">Resultater av tilstandsanalyse</Typography>
        <Typography sx={{ my: 3 }} variant="h6">
          Antall kategorier: {categoriesAmount}
        </Typography>
        <Divider sx={{ my: 3 }} />
        <Stack style={{ justifyItems: "center" }} spacing={2} direction={"row"}>
          <ButtonGroup
            style={{ justifyContent: "center", alignItems: "center" }}
            variant="outlined"
            aria-label="Basic button group"
          >
            <DownloadPDF
              results={results}
              categories={categories}
              company={company}
              assessment={assessment}
              spiderImage={spiderImage}
              barImage={barImage}
              secondBarImage={secondBarImage}
            />

            <Button href={`/tilstandsanalyse/${assessmentId}`}>
              Tilbake til tilstandsanalyse
            </Button>
          </ButtonGroup>
          <TextField
            id="outlined-basic"
            label="Antall tiltak"
            defaultValue={mpAmount}
            variant="outlined"
            type="number"
            onChange={(e) => {
              const value = Number(e.currentTarget.value);
              setMpAmount(value === 0 ? 1 : value);
            }}
          />
        </Stack>

        <Divider sx={{ my: 3 }} />
        <FilterCheckBox
          handleCheckboxSelection={handleCheckboxSelection}
          categoriesAmount={categoriesAmount}
        />
        <Divider sx={{ my: 3 }} />
        <Typography variant="h6" gutterBottom>
          Diagrammer
        </Typography>
        <Diagrams
          categories={categories}
          filter={filter}
          results={results}
          setSpiderImage={setSpiderImage}
          setBarImage={setBarImage}
          setSecondBarImage={setSecondBarImage}
        />
        <Divider sx={{ my: 3 }} />

        <MostPressing categories={categories} results={results} />
      </Container>
    </main>
  );
};

const FilterCheckBox = ({
  handleCheckboxSelection,
  categoriesAmount,
}: {
  handleCheckboxSelection: (value: string) => void;
  categoriesAmount: number;
}) => {
  return (
    <>
      <Typography variant="h6" gutterBottom>
        Velg diagrammer
      </Typography>
      <FormGroup>
        {categoriesAmount > 2 && (
          <FormControlLabel
            control={
              <Checkbox
                onChange={() => handleCheckboxSelection("Modenhetsnivå spider")}
              />
            }
            label="Modenhetsnivå spider"
          />
        )}

        <FormControlLabel
          control={
            <Checkbox
              onChange={() =>
                handleCheckboxSelection("Avstand fra ønsket tilstand")
              }
            />
          }
          label="Avstand fra ønsket tilstand"
        />
        <FormControlLabel
          control={
            <Checkbox
              onChange={() =>
                handleCheckboxSelection("Fullførte mål søylediagram")
              }
            />
          }
          label="Fullførte mål søylediagram"
        />
      </FormGroup>
    </>
  );
};

export default App;
