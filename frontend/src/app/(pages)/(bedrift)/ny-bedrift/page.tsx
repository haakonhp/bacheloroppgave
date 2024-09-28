"use client";
import TextFieldComponent from "@/app/components/bedrift/TextFieldComponent";
import CardComponent from "@/app/components/shared/Card";
import { Alert, Box, Button, Container, Snackbar } from "@mui/material";
import React, { useState } from "react";

const Page = () => {
  const [companyData, setCompanyData] = useState({
    companyName: "",
    companySector: "",
    numberOfEmployees: 0,
    contactPerson: "",
    contactNumber: 0,
    contactEmail: "",
  });

  const updateFormData = (e: {
    target: { name: any; value: any; type: string };
  }) => {
    const { name, value, type } = e.target;
    const parsedValue = type === "number" ? Number(value) : value;
    setCompanyData({ ...companyData, [name]: parsedValue });
  };

  const [open, setOpen] = useState(false);
  const [error, setError] = useState(false);

  const handleClose = (
    event?: React.SyntheticEvent | Event,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
    setError(false);
  };

  const createCompany = async () => {
    if (
      !companyData.companyName ||
      !companyData.companySector ||
      !companyData.contactEmail ||
      !companyData.contactNumber ||
      !companyData.contactPerson ||
      !companyData.numberOfEmployees
    ) {
      setError(true);
      return null;
    } else {
      try {
        const response = await fetch("http://localhost:8080/corporations", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(companyData),
        });
        setOpen(true);
      } catch (error) {
        console.log(error);
        setError(true);
      }
    }
  };

  return (
    <>
      <Container>
        <CardComponent title={"Opprett ny bedrift"}>
          <Box sx={{ display: "flex", flexDirection: "column" }}>
            <TextFieldComponent
              label="Bedriftnavn"
              data="companyName"
              type={"text"}
              disabled={false}
              dataVar={companyData.companyName}
              updateFormData={updateFormData}
            />
            <TextFieldComponent
              label="Bransje"
              data="companySector"
              type={"text"}
              disabled={false}
              dataVar={companyData.companySector}
              updateFormData={updateFormData}
            />
            <TextFieldComponent
              label="Antall ansatte"
              data="numberOfEmployees"
              type={"number"}
              disabled={false}
              dataVar={companyData.numberOfEmployees}
              updateFormData={updateFormData}
            />
            <TextFieldComponent
              label="Kontakt person"
              data="contactPerson"
              type={"text"}
              disabled={false}
              dataVar={companyData.contactPerson}
              updateFormData={updateFormData}
            />
            <TextFieldComponent
              label="Kontakt nummer"
              data="contactNumber"
              type={"number"}
              disabled={false}
              dataVar={companyData.contactNumber}
              updateFormData={updateFormData}
            />
            <TextFieldComponent
              label="E-post"
              data="contactEmail"
              type={"email"}
              disabled={false}
              dataVar={companyData.contactEmail}
              updateFormData={updateFormData}
            />
          </Box>
          <Button href="/bedrift" variant="outlined" sx={{ mr: 1 }}>
            Tilbake
          </Button>
          <Button variant="contained" onClick={createCompany}>
            Opprett
          </Button>
        </CardComponent>
      </Container>
      <Snackbar open={error} autoHideDuration={6000} onClose={handleClose}>
        <Alert
          onClose={handleClose}
          severity="error"
          variant="filled"
          sx={{ width: "100%" }}
        >
          Noe gikk galt. Sjekk at du har lagt inn all informasjon.
        </Alert>
      </Snackbar>
      <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <Alert
          onClose={handleClose}
          severity="success"
          variant="filled"
          sx={{ width: "100%" }}
        >
          Vellykket opprettelse av bedrift
        </Alert>
      </Snackbar>
    </>
  );
};

export default Page;
