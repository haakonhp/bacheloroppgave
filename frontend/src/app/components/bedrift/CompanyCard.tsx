import React from "react";
import { Card, CardContent, Stack } from "@mui/material";
import BusinessIcon from "@mui/icons-material/Business";
import { CompanyType } from "@/types/CompanyType";
import { useRouter } from "next/navigation";

const CompanyCard = ({
  companies,
  direction,
}: {
  companies: CompanyType[];
  direction: "row" | "column";
}) => {
  const router = useRouter();

  return (
    <>
      <Stack direction={direction}>
        {companies.map((item) => (
          <Card
            onClick={() => {
              router.push(`/bedrift/${item.id}`);
            }}
            key={item.id}
            sx={{
              width: 345,
              mb: 3,
              mr: 3,
              bgcolor: "#D7EEF6	",
              "&:hover": {
                background: "#f9f9f9",
                cursor: "pointer",
              },
            }}
          >
            <CardContent>
              <Stack direction="row" spacing={1}>
                <BusinessIcon fontSize="large" />
                <Stack direction="column" spacing={1}>
                  <p>{item.companyName}</p>
                  <p>Bransje: {item.companySector}</p>
                  <p>Ansatte: {item.numberOfEmployees}</p>
                </Stack>
              </Stack>
            </CardContent>
          </Card>
        ))}
      </Stack>
    </>
  );
};

export default CompanyCard;
