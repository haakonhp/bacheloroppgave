"use client";
import React, { useEffect, useState } from "react";
import CompanyTable from "../../../components/bedrift/CompanyTable";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import CompanyCard from "@/app/components/bedrift/CompanyCard";
import { CompanyType } from "@/types/CompanyType";
import BusinessIcon from "@mui/icons-material/Business";
import { Stack, TextField } from "@mui/material";
import ViewMode from "@/app/components/bedrift/ViewMode";
import Loading from "@/app/components/shared/Loading";
import Divider from "@mui/material/Divider";

const Page = () => {
  const [view, setView] = useState(1);
  const [companies, setCompanies] = useState<CompanyType[] | null>(null);
  const [filter, setFilter] = useState("");

  const fetchCompanies = async () => {
    try {
      const response = await fetch(`http://localhost:8080/corporations`);
      const result = await response.json();
      setCompanies(result);
    } catch (error) {
      console.error("Error fetching companies:", error);
      setCompanies([]);
    }
  };

  useEffect(() => {
    fetchCompanies();
  }, []);

  if (companies === null) {
    return <Loading />;
  } else if (companies.length > 0) {
    const filteredCompanies = companies.filter((company) =>
      company.companyName.toLowerCase().includes(filter.toLowerCase())
    );
    return (
      <main className="flex flex-col items-center min-h-screen p-4 sm:p-8">
        <Container maxWidth="lg">
          <Stack direction="row" spacing={1} alignItems="center">
            <BusinessIcon />
            <Typography variant="h4" gutterBottom>
              Bedrifter
            </Typography>
          </Stack>
          <ViewMode view={view} setView={setView} />
          <Divider sx={{ mb: 2, mt: 2 }} />
          <p style={{ marginBottom: 10 }}>Filtrer etter</p>
          <TextField
            id="outlined-basic"
            label="Bedriftnavn..."
            variant="outlined"
            onChange={(e) => {
              setFilter(e.currentTarget.value);
            }}
          />
          <Divider sx={{ mb: 2, mt: 2 }} />

          {view === 1 && <CompanyTable companies={filteredCompanies} />}
          {view === 2 && (
            <CompanyCard direction="column" companies={filteredCompanies} />
          )}
          {view === 3 && (
            <CompanyCard direction="row" companies={filteredCompanies} />
          )}
          <Divider sx={{ mb: 2, mt: 2 }} />
          <Button href="/ny-bedrift" variant="contained">
            Ny bedrift
          </Button>
        </Container>
      </main>
    );
  } else {
    return (
      <Container maxWidth="lg">
        <Stack direction="row" spacing={1} alignItems="center">
          <BusinessIcon />
          <Typography variant="h4" gutterBottom>
            Bedrifter
          </Typography>
        </Stack>
        <p>Fant ingen bedrifter</p>
        <Divider sx={{ mt: 3, mb: 3 }} />
        <Button href="/ny-bedrift" variant="contained">
          Ny bedrift
        </Button>
      </Container>
    );
  }
};

export default Page;
