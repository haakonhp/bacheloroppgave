"use client";
import CompanyBanner from "@/app/components/bedrift/CompanyBanner";
import CompanyAssessments from "@/app/components/bedrift/CompanyAssessments";
import { Button, Container } from "@mui/material";
import React, { useEffect, useState } from "react";
import { CompanyType } from "@/types/CompanyType";
import Loading from "@/app/components/shared/Loading";

export default function Page({ params }: { params: { id: string } }) {
  const companyId = params.id;
  const [companyData, setCompanyData] = useState<CompanyType | null>(null);

  const fetchCompany = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/corporations/${companyId}`
      );
      const result = await response.json();
      setCompanyData(result);
    } catch (error) {
      console.error("Error fetching company:", error);
      setCompanyData(null);
    }
  };

  useEffect(() => {
    fetchCompany();
  }, [companyId]);

  if (companyData === null) {
    return <Loading />;
  } else
    return (
      <>
        <Container>
          <CompanyBanner
            companyData={companyData}
            fetchCompany={fetchCompany}
          />
          <br />
          <CompanyAssessments companyId={companyId} />
          <br />
          <Button
            href={`/ny-tilstandsanalyse/${companyId}`}
            variant="contained"
          >
            Ny tilstandsanalyse
          </Button>
        </Container>
      </>
    );
}
