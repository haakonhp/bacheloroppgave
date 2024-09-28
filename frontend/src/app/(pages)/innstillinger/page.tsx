"use client";
import {
  Button,
  CircularProgress,
  Container,
  Divider,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import CloudDownloadIcon from "@mui/icons-material/CloudDownload";

const Page = () => {
  // XLSX
  const [xlsxSuccess, setXlxsSuccess] = useState<boolean>(false);
  const [xlsxFile, setXlsxFile] = useState<File | null>(null);
  const [xlsxLoading, setXlsxLoading] = useState(false);
  const [xlsxTypeError, setXlsxTypeError] = useState<null | string>(null);

  // SQL
  const [sqlSuccess, setSqlSuccess] = useState<boolean>(false);
  const [sqlFile, setSqlFile] = useState<File | null>(null);
  const [sqlLoading, setSqlLoading] = useState(false);
  const [sqlTypeError, setSqlTypeError] = useState<null | string>(null);

  // FILE HANDLER.
  // Noe inspirasjon fra https://github.com/HamzaAnwar1998/Upload-And-View-Excel-Files/blob/main/src/App.js
  // Lagt til egen logikk for å håndtere file uploads dynamisk.

  const handleFile = (
    e: React.ChangeEvent<HTMLInputElement>,
    filesPermitted: string,
    fileSetter: React.Dispatch<React.SetStateAction<File | null>>,
    typeError: React.Dispatch<React.SetStateAction<string | null>>
  ) => {
    if (e.target && e.target.files && e.target.files.length > 0) {
      const selectedFile = e.target.files[0];
      const fileExtension = selectedFile.name.split(".").pop()?.toLowerCase();

      const fileTypesMapping: any = {
        SQL: ["sql"],
        XLSX: ["xlsx"],
      };

      if (
        selectedFile &&
        fileTypesMapping[filesPermitted] &&
        fileTypesMapping[filesPermitted].includes(fileExtension)
      ) {
        typeError(null);
        // SQL filer
        if (fileExtension === "sql") {
          let reader = new FileReader();
          reader.readAsText(selectedFile);
          reader.onload = (e) => {
            if (e.target && e.target.result) {
              fileSetter(selectedFile);
            }
          };
          // XLSX filer
        } else if (fileExtension === "xlsx") {
          fileSetter(selectedFile);
        }
      } else {
        typeError(`You can only upload ${filesPermitted} files.`);
        fileSetter(null);
      }
    }
  };

  // XLSX FILE UPLOAD
  const handleXLSXUpload = async () => {
    if (!xlsxFile) {
      setXlsxTypeError("Ingen fil er valgt");
      return;
    }
    setXlsxLoading(true);
    try {
      const response = await fetch("http://localhost:8080/database/document", {
        method: "POST",
        headers: {
          "Content-Type":
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        },
        body: xlsxFile,
      });
      if (!response.ok) {
        setXlsxLoading(false);
        console.log("Feil response");
        return;
      }
      setXlsxLoading(false);

      setXlxsSuccess(true);
    } catch (error) {
      console.log(error);
      setXlsxLoading(false);
      setXlxsSuccess(false);
    }
  };

  // SQL FILE UPLOAD

  const handleSQLUpload = async () => {
    if (!sqlFile) {
      setSqlTypeError("Ingen fil er valgt");
      return;
    }

    setSqlLoading(true);
    try {
      const response = await fetch("http://localhost:8080/database/backup", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-sql",
        },
        body: sqlFile,
      });
      if (!response.ok) {
        setSqlLoading(false);
        console.log("Feil response");
        return;
      }
      setSqlLoading(false);
      setSqlSuccess(true);
    } catch (error) {
      console.log(error);
      setSqlLoading(false);
      setSqlSuccess(false);
    }
  };

  const handleDownload = async () => {
    try {
      const response = await fetch("http://localhost:8080/database/backup", {
        method: "GET",
      });

      if (!response.ok) {
        console.error("Failed to retrieve backup");
        return;
      }

      const blob = await response.blob();
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = "backup.sql";
      document.body.appendChild(a);
      a.click();
      window.URL.revokeObjectURL(url);
    } catch (error) {
      console.error("Feilet med å laste ned sql backup");
    }
  };

  return (
    <>
      <Container maxWidth="lg">
        <Typography variant="h3">Oppdatering av kontroller</Typography>
        <Stack spacing={3}>
          <Divider sx={{ my: 2 }} />
          <Typography variant="h5">Last opp Excel-fil</Typography>
          <TextField
            type="file"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              handleFile(e, "XLSX", setXlsxFile, setXlsxTypeError)
            }
          />
          {xlsxSuccess && <p>Opplastet</p>}
          {xlsxTypeError && <p>{xlsxTypeError}</p>}
          {!xlsxLoading ? (
            <Button
              variant="contained"
              color="primary"
              startIcon={<CloudUploadIcon />}
              onClick={handleXLSXUpload}
            >
              Last opp Excel-fil
            </Button>
          ) : (
            <CircularProgress />
          )}
          <Divider sx={{ my: 2 }} />
          <Typography variant="h5">Last opp backup-fil</Typography>
          <TextField
            type="file"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              handleFile(e, "SQL", setSqlFile, setSqlTypeError)
            }
          />
          {sqlSuccess && <p>Opplastet</p>}
          {sqlTypeError && <p>{sqlTypeError}</p>}
          {!sqlLoading ? (
            <Button
              variant="contained"
              color="primary"
              startIcon={<CloudUploadIcon />}
              onClick={handleSQLUpload}
            >
              Last opp backup-fil
            </Button>
          ) : (
            <CircularProgress />
          )}
          <Divider sx={{ my: 2 }} />
          <Typography variant="h5">Last ned backup-fil</Typography>
          <Button
            variant="contained"
            color="primary"
            startIcon={<CloudDownloadIcon />}
            onClick={handleDownload}
          >
            Last ned backup-fil
          </Button>
        </Stack>
      </Container>
    </>
  );
};

export default Page;
