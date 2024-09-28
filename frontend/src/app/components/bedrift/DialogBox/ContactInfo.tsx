import { CompanyType } from "@/types/CompanyType";

export const ContactInfo = ({ companyData }: { companyData: CompanyType }) => {
  return (
    <>
      <p>Kontakt person: {companyData.contactPerson}</p>
      <p>Kontakt nummer: {companyData.contactNumber}</p>
      <p>Kontakt email: {companyData.contactEmail}</p>
    </>
  );
};
