import { CompanyType } from "@/types/CompanyType";
import { Alert, Box, Button, Divider, Snackbar } from "@mui/material";
import { useState } from "react";
import EditIcon from "@mui/icons-material/Edit";
import SaveIcon from "@mui/icons-material/Save";
import CancelIcon from "@mui/icons-material/Cancel";
import TextFieldComponent from "../TextFieldComponent";

export const EditInfo = ({
  companyData,
  fetchCompany,
}: {
  companyData: CompanyType;
  fetchCompany: () => Promise<void>;
}) => {
  const [editedCompanyData, setCompanyData] = useState({
    id: companyData.id,
    companyName: companyData.companyName,
    companySector: companyData.companySector,
    numberOfEmployees: companyData.numberOfEmployees,
    contactPerson: companyData.contactPerson,
    contactNumber: companyData.contactNumber,
    contactEmail: companyData.contactEmail,
  });

  const [disabled, setDisabled] = useState(true);
  const [open, setOpen] = useState(false);
  const [error, setError] = useState(false);

  const updateFormData = (e: {
    target: { name: any; value: any; type: string };
  }) => {
    const { name, value, type } = e.target;
    const parsedValue = type === "number" ? Number(value) : value;
    setCompanyData({ ...editedCompanyData, [name]: parsedValue });
  };

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

  const editCompany = async () => {
    if (
      !editedCompanyData.companyName ||
      !editedCompanyData.companySector ||
      !editedCompanyData.contactEmail ||
      !editedCompanyData.contactNumber ||
      !editedCompanyData.contactPerson ||
      !editedCompanyData.numberOfEmployees
    ) {
      setError(true);
      return null;
    } else {
      try {
        const response = await fetch(
          `http://localhost:8080/corporations/${companyData.id}`,
          {
            method: "PATCH",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(editedCompanyData),
          }
        );
        if (response.ok) {
          setOpen(true);
          fetchCompany();
        }
      } catch (error) {
        console.log(error);
        setError(true);
      }
    }
  };

  return (
    <>
      <Box sx={{ display: "flex", flexDirection: "column" }}>
        <TextFieldComponent
          label="Bedriftnavn"
          data="companyName"
          type={"text"}
          disabled={disabled}
          dataVar={editedCompanyData.companyName}
          updateFormData={updateFormData}
        />
        <TextFieldComponent
          label="Bransje"
          data="companySector"
          type={"text"}
          dataVar={editedCompanyData.companySector}
          disabled={disabled}
          updateFormData={updateFormData}
        />
        <TextFieldComponent
          label="Antall ansatte"
          data="numberOfEmployees"
          type={"number"}
          dataVar={editedCompanyData.numberOfEmployees}
          disabled={disabled}
          updateFormData={updateFormData}
        />
        <TextFieldComponent
          label="Kontakt person"
          data="contactPerson"
          type={"text"}
          dataVar={editedCompanyData.contactPerson}
          disabled={disabled}
          updateFormData={updateFormData}
        />
        <TextFieldComponent
          label="Kontakt nummer"
          data="contactNumber"
          type={"number"}
          dataVar={editedCompanyData.contactNumber}
          disabled={disabled}
          updateFormData={updateFormData}
        />
        <TextFieldComponent
          label="E-post"
          data="contactEmail"
          type={"email"}
          dataVar={editedCompanyData.contactEmail}
          disabled={disabled}
          updateFormData={updateFormData}
        />
      </Box>
      <Divider style={{ marginTop: 10, marginBottom: 10 }} />

      <Button
        onClick={() => {
          setDisabled(!disabled);
        }}
        startIcon={!disabled ? <CancelIcon /> : <EditIcon />}
      >
        {disabled ? "Endre" : "Kanseller"}
      </Button>

      {!disabled && (
        <Button onClick={editCompany} startIcon={<SaveIcon />}>
          Lagre
        </Button>
      )}
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
          Vellykket endring av bedrift
        </Alert>
      </Snackbar>
    </>
  );
};
