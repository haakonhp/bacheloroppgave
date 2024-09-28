"use client";
import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { useRouter } from "next/navigation";
import { CompanyType } from "@/types/CompanyType";

export default function CompanyTable({
  companies,
}: {
  companies: CompanyType[];
}) {
  const router = useRouter();
  return (
    <TableContainer sx={{ mb: 2 }} component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Navn</TableCell>
            <TableCell>Bransje</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {companies.map((row) => (
            <TableRow
              key={row.id}
              sx={{
                "&:last-child td, &:last-child th": { border: 0 },
                "&:hover": {
                  background: "#f9f9f9",
                  cursor: "pointer",
                },
              }}
            >
              <TableCell
                component="th"
                scope="row"
                onClick={() => {
                  router.push(`/bedrift/${row.id}`);
                }}
              >
                {row.companyName}
              </TableCell>
              <TableCell component="th" scope="row">
                {row.companySector}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
