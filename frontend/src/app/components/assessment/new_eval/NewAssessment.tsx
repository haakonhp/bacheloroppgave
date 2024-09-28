"use client";
import React, { useEffect, useState } from "react";
import CardComponent from "../../shared/Card";
import {
  Alert,
  Avatar,
  Box,
  Button,
  Divider,
  Snackbar,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import TransferList from "../../shared/TransferList";
import EvaluationSource from "./EvaluationSource";
import { useRouter } from "next/navigation";
import EvalControllers from "./EvalControllers";

const NewAssessment = ({ companyId }: { companyId: string }) => {
  const [assessmentName, setAssessmentName] = useState<string>("");

  // Select boxes av sources
  const [selectedSources, setSelectedSources] = useState<[] | string[]>([]);

  // 2. State som henter alle unike rammeverk basert på sources.
  const [sourceFrameworks, setSourceFrameworks] = useState<[] | string[]>([]);

  // Select boxes av rammeverk
  const [selectedFrameworks, setSelectedFrameworks] = useState<[] | string[]>(
    []
  );

  // 3. State som henter alle kontroller basert på source og framework.
  const [controls, setControls] = useState<[{}] | []>([]);

  const [open, setOpen] = useState(false);

  const handleClose = (
    event?: React.SyntheticEvent | Event,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }
    setOpen(false);
  };

  useEffect(() => {
    const fetchFrameworks = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/frameworks?sources=${selectedSources}`,
          {
            method: "GET",
          }
        );
        const result = await response.json();
        setSourceFrameworks(result);
      } catch (error) {
        console.error("Error fetching company:", error);
      }
    };

    fetchFrameworks();
  }, [selectedSources]);

  const fetchControllers = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/controls?sources=${selectedSources}&frameworks=${selectedFrameworks}`,
        {
          method: "GET",
        }
      );
      const result = await response.json();
      setControls(result);
    } catch (error) {
      console.error("Error fetching results:", error);
      setControls([]);
    }
  };

  useEffect(() => {
    fetchControllers();
  }, [selectedFrameworks, selectedSources]);

  const router = useRouter();

  const createEvaluation = async () => {
    var evalData = {
      evalName: assessmentName,
      corporation: companyId,
      sources: selectedSources,
      frameworks: selectedFrameworks,
    };
    try {
      const response = await fetch("http://localhost:8080/evaluations", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(evalData),
      });

      if (response.ok) {
        router.push(`/bedrift/${companyId}`);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const runCreation = async () => {
    if (assessmentName === "" || controls.length === 0) {
      setOpen(true);
      return null;
    } else {
      await createEvaluation();
    }
  };

  return (
    <>
      <CardComponent title="Ny tilstandsanalayse">
        <Stack spacing={3}>
          <TextField
            id="outlined-basic"
            label="Navn på tilstandsanalyse"
            variant="outlined"
            onChange={(e) => {
              setAssessmentName(e.currentTarget.value);
            }}
          />
          <Divider />
          <Stack sx={{ justifyContent: "space-evenly" }} direction="row">
            <div>
              <Typography sx={{ fontSize: 20 }} gutterBottom>
                Velg kilde
              </Typography>
              <Divider sx={{ mb: 2 }} />

              <EvaluationSource setSelectedSources={setSelectedSources} />
            </div>
            <Divider orientation="vertical" flexItem sx={{ mr: 2, ml: 2 }} />
            <div>
              <Typography sx={{ fontSize: 20 }} gutterBottom>
                Velg rammeverk/standard
              </Typography>
              <Divider sx={{ mb: 2 }} />

              {selectedSources && (
                <TransferList
                  sourceFrameworks={sourceFrameworks}
                  selectedFrameworks={selectedFrameworks}
                  setSelectedFrameworks={setSelectedFrameworks}
                />
              )}
            </div>
          </Stack>
          <Divider />
          <EvalControllers controlsLength={controls?.length} />
          <Button onClick={runCreation} variant="contained">
            Lagre
          </Button>
        </Stack>
        <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
          <Alert
            onClose={handleClose}
            severity="error"
            variant="filled"
            sx={{ width: "100%" }}
          >
            Mangler navn eller kontroller
          </Alert>
        </Snackbar>
      </CardComponent>
    </>
  );
};

export default NewAssessment;
