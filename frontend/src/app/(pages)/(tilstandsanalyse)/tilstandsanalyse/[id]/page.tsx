"use client";
import { Container } from "@mui/material";
import { useEffect, useState } from "react";
import CategoryTabs from "@/app/components/assessment/CategoryTabs";
import AssessmentInfo from "@/app/components/assessment/AssessmentInfo";
import Loading from "@/app/components/shared/Loading";
import AssessmentType, { NewAnswer } from "@/types/AssessmentType";
import { CompanyType } from "@/types/CompanyType";

const Page = ({ params }: { params: { id: string } }) => {
  const assessmentId = params.id;
  const [assessment, setAssessment] = useState<AssessmentType | null>(null);
  const [companyData, setCompanyData] = useState<CompanyType | null>(null);

  const [newAnswerList, setNewAnswerList] = useState<NewAnswer[]>([]);
  const [open, setOpen] = useState(false);

  const [categoryMap, setCategoryMap] = useState(new Set());

  const fetchAssessment = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/evaluations/${assessmentId}`
      );
      const result = (await response.json()) as AssessmentType;
      await fetchCompany(result.corporation);
      setAssessment(result);
    } catch (error) {
      console.error("Error fetching company:", error);
    }
  };

  const fetchCompany = async (companyId: string) => {
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
    fetchAssessment();
  }, [assessmentId]);

  const saveAnswers = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/evaluations/answers/${assessmentId}`,
        {
          method: "PATCH",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ answerList: newAnswerList }),
        }
      );
      if (response.ok) {
        setOpen(true);
        setNewAnswerList([]);
      }
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    if (assessment) {
      // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set/add
      const categories = new Set();
      assessment.answers.forEach((item) =>
        categories.add(item.control.cyberDefenseFunction)
      );

      // https://www.geeksforgeeks.org/how-to-sort-a-set-in-javascript/#approach-2-using-arrayfrom-method
      const sortedCategories = new Set(Array.from(categories).sort());
      setCategoryMap(sortedCategories);
    }
  }, [assessment]);

  if (assessment === null || companyData === null) {
    return <Loading />;
  } else {
    return (
      <>
        <main className="flex flex-col items-center min-h-screen p-4 sm:p-8">
          <Container>
            <AssessmentInfo
              companyData={companyData}
              assessmentName={assessment.name}
              assessmentId={assessmentId}
              saveAnswers={saveAnswers}
            />

            <CategoryTabs
              categoryMap={categoryMap}
              controls={assessment.answers}
              setAssessment={setAssessment}
              saveAnswers={saveAnswers}
              setOpen={setOpen}
              newAnswerList={newAnswerList}
              open={open}
              setNewAnswerList={setNewAnswerList}
            />
          </Container>
        </main>
      </>
    );
  }
};

export default Page;
