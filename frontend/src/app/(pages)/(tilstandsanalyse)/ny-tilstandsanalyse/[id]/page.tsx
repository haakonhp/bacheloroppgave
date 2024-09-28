import NewAssessment from "@/app/components/assessment/new_eval/NewAssessment";
import { Container } from "@mui/material";
import React from "react";

export default function Page({ params }: { params: { id: string } }) {
  const companyId = params.id;

  return (
    <>
      <main className="flex flex-col items-center min-h-screen p-4 sm:p-8">
        <Container maxWidth="lg">
          <NewAssessment companyId={companyId} />
        </Container>
      </main>
    </>
  );
}
