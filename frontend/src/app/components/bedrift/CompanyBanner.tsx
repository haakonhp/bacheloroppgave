import {
  Card,
  CardMedia,
  CardContent,
  Typography,
  CardActions,
  Button,
  Chip,
  Stack,
  Divider,
} from "@mui/material";
import React, { useState } from "react";
import HomeRepairServiceIcon from "@mui/icons-material/HomeRepairService";
import { CompanyType } from "@/types/CompanyType";
import DialogComponent from "../shared/Dialog";
import DeleteIcon from "@mui/icons-material/Delete";
import ContactPageIcon from "@mui/icons-material/ContactPage";
import { EditInfo } from "./DialogBox/EditInfo";
import { useRouter } from "next/navigation";
import CancelIcon from "@mui/icons-material/Cancel";

const CompanyBanner = ({
  companyData,
  fetchCompany,
}: {
  companyData: CompanyType;
  fetchCompany: () => Promise<void>;
}) => {
  const [openEdit, setOpenEdit] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);

  const router = useRouter();

  const deleteCompany = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/corporations/${companyData.id}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (response.ok) {
        router.push("/bedrift");
      }
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      <Card>
        <CardMedia
          sx={{ height: 300 }}
          image="/companyBanner.svg"
          title="Company banner"
        />
        <CardContent>
          <Stack direction="row">
            <Typography
              sx={{ mr: 3 }}
              gutterBottom
              variant="h5"
              component="div"
            >
              {companyData.companyName}
            </Typography>
            <Chip
              icon={<HomeRepairServiceIcon />}
              label={companyData.companySector}
              variant="outlined"
            />
          </Stack>
        </CardContent>
        <CardActions>
          <Button
            variant="outlined"
            size="small"
            onClick={() => setOpenEdit(true)}
            startIcon={<ContactPageIcon />}
          >
            Kontakt
          </Button>
          <Button
            variant="outlined"
            color="error"
            size="small"
            onClick={() => setOpenDelete(true)}
            startIcon={<DeleteIcon />}
          >
            Slett
          </Button>
        </CardActions>
      </Card>

      <DialogComponent
        title={companyData.companyName}
        open={openEdit}
        setOpen={setOpenEdit}
      >
        <EditInfo companyData={companyData} fetchCompany={fetchCompany} />
      </DialogComponent>

      <DialogComponent
        title={companyData.companyName}
        open={openDelete}
        setOpen={setOpenDelete}
      >
        <>
          <p>
            Er du sikker på at du vil slette denne bedriften? Du mister alle
            tilstandsanalyser til denne bedriften.
          </p>
          <Divider style={{ marginTop: 10, marginBottom: 10 }} />

          <Button
            color="error"
            onClick={deleteCompany}
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

export default CompanyBanner;
