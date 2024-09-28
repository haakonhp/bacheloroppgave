import React, { useState } from "react";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import ContactsIcon from "@mui/icons-material/Contacts";
import { Button, Divider, Stack } from "@mui/material";
import { CompanyType } from "@/types/CompanyType";
import DeleteIcon from "@mui/icons-material/Delete";
import { useRouter } from "next/navigation";
import DialogComponent from "../shared/Dialog";
import CancelIcon from "@mui/icons-material/Cancel";

const AssessmentInfo = ({
  companyData,
  assessmentName,
  assessmentId,
  saveAnswers,
}: {
  companyData: CompanyType;
  assessmentName: String;
  assessmentId: String;
  saveAnswers: () => Promise<void>;
}) => {
  const [openDelete, setOpenDelete] = useState(false);

  const router = useRouter();
  const deleteAssessment = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/evaluations/${assessmentId}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (response.ok) {
        router.push(`/bedrift/${companyData.id}`);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <Card sx={{ mb: 5 }}>
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            <ContactsIcon /> Tilstandsanalyse {assessmentName}
          </Typography>
          <Divider sx={{ mb: 2 }} />
          <Typography sx={{ mb: 1.5 }} color="text.secondary">
            Bedrift: {companyData.companyName}
          </Typography>
          <Stack direction="row" spacing={2}>
            <Button
              onClick={() => {
                saveAnswers();
                router.push(`/bedrift/${companyData.id}`);
              }}
              variant="contained"
            >
              Til bedrift
            </Button>
            <Button
              onClick={() => {
                saveAnswers();
                router.push(`/tilstandsanalyse/resultat/${assessmentId}`);
              }}
              variant="contained"
            >
              Resultat
            </Button>
            <Button
              color="error"
              variant="contained"
              startIcon={<DeleteIcon />}
              onClick={() => setOpenDelete(true)}
            >
              Slett
            </Button>
          </Stack>
        </CardContent>
      </Card>

      <DialogComponent
        title={companyData.companyName}
        open={openDelete}
        setOpen={setOpenDelete}
      >
        <>
          <p>Er du sikker at du vil slette denne tilstandsanalysen?</p>
          <Divider style={{ marginTop: 10, marginBottom: 10 }} />

          <Button
            color="error"
            onClick={deleteAssessment}
            startIcon={<DeleteIcon />}
          >
            Ja
          </Button>
          <Button
            onClick={() => setOpenDelete(false)}
            startIcon={<CancelIcon />}
          >
            Nei
          </Button>
        </>
      </DialogComponent>
    </>
  );
};

export default AssessmentInfo;
