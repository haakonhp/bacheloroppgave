import React from "react";
import {
  Page,
  View,
  Document,
  StyleSheet,
  PDFDownloadLink,
  Text,
  Font,
} from "@react-pdf/renderer";
import { ResultType } from "@/types/ResultType";
import AssessmentType from "@/types/AssessmentType";
import { MostPressing } from "./MostPressing";
import { Button } from "@mui/material";
import IntroPage from "./IntroPage";
import { CompanyType } from "@/types/CompanyType";
import { Table, TR, TH, TD } from "@ag-media/react-pdf-table";
import { format } from "date-fns";
import ContentBox from "./ContentBox";
import { DiagramImage } from "./DiagramImage";

export const PDFCreate = ({
  results,
  categories,
  company,
  assessment,
  spiderImage,
  barImage,
  secondBarImage,
}: {
  results: any;
  categories: string[];
  company: CompanyType;
  assessment: AssessmentType;
  spiderImage: string | undefined;
  barImage: string | undefined;
  secondBarImage: string | undefined;
}) => {
  const number = [
    "en",
    "to",
    "tre",
    "fire",
    "fem",
    "seks",
    "syv",
    "åtte",
    "ni",
    "ti",
    "elleve",
    "tolv",
  ];
  const styles = StyleSheet.create({
    body: {
      padding: 20,
      alignItems: "center",
      width: "210cm",
      justifyContent: "space-around",
    },
    mpBody: {
      padding: 20,
      alignItems: "center",
      width: "210cm",
    },
    title: {
      marginBottom: 5,
      fontSize: 14,
      textAlign: "justify",
      fontFamily: "Oswald",
      textTransform: "uppercase",
    },
    text: {
      marginBottom: 10,
      fontSize: 12,
      textAlign: "justify",
      fontFamily: "Helvetica",
    },
    pageNumber: {
      position: "absolute",
      fontSize: 12,
      bottom: 30,
      left: 0,
      right: 0,
      textAlign: "center",
      color: "grey",
    },
  });

  Font.register({
    family: "Oswald",
    src: "https://fonts.gstatic.com/s/oswald/v13/Y_TKV6o8WovbUd3m_X9aAA.ttf",
  });

  var mpLength = -1;
  categories.forEach((category) => {
    mpLength += results.categories[category].mostPressing.length;
  });

  return (
    <Document>
      <IntroPage
        categories={categories}
        assessment={assessment}
        company={company}
      />
      <Page style={styles.body}>
        <ContentBox
          styles={styles}
          title="Modenhetsnivå"
          description="Dette diagramet viser gjennomsnittlig modenhetsnivå for både ønsket og oppnådd tilstand. Resultatet regnes ut med høyde for prioritene til hver kontroll, slik at viktigere kontroller påvirker resultatet mer."
        >
          <DiagramImage imgWidth={450} imgHeight={450} image={spiderImage} />
        </ContentBox>
        <ContentBox
          styles={styles}
          title="Resultater"
          description="Denne tabellen viser gjennomsnittlig modenhetsnivå for ønsket og oppnådd tilstand vektet med prioritet pr. kategori. Tabellen inkluderer også en sum av antall kontroller med oppnådd tilstand som er lik ønsket tilstand, samt totalt antall kontroller pr. kategori."
        >
          <Table tdStyle={{ padding: "2px" }} style={styles.text}>
            <TH>
              <TD style={{ justifyContent: "center" }}>Kategori</TD>
              <TD style={{ justifyContent: "center" }}>Snitt oppnådd</TD>
              <TD style={{ justifyContent: "center" }}>Snitt ønsket</TD>
              <TD style={{ justifyContent: "center" }}>Fullførte kontroller</TD>
              <TD style={{ justifyContent: "center" }}>Antall kontroller</TD>
            </TH>
            {categories.map((category, index) => (
              <TR
                style={{
                  backgroundColor: index % 2 === 0 ? "#ddd" : undefined,
                }}
                key={index}
              >
                <TD>{category}</TD>
                <TD>
                  {results.categories[category].rawGapScoreAverage.toFixed(2)}
                </TD>
                <TD>
                  {results.categories[category].desiredGapScoreAverage.toFixed(
                    2
                  )}
                </TD>
                <TD>{results.categories[category].satisfactoryElements}</TD>
                <TD>{results.categories[category].categoryElements}</TD>
              </TR>
            ))}
          </Table>
        </ContentBox>
        <Text
          style={styles.pageNumber}
          render={({ pageNumber, totalPages }) =>
            `${pageNumber} / ${totalPages}`
          }
          fixed
        />
      </Page>
      <Page style={styles.body}>
        <View
          style={{ flexDirection: "column", alignItems: "center" }}
          wrap={false}
        >
          <ContentBox
            styles={styles}
            title="Fullførte kontroller"
            description="Dette diagrammet viser sum antall kontroller som har oppnådd tilstand lik ønsket tilstand pr. kategori."
          >
            <DiagramImage
              imgHeight={242}
              imgWidth={484}
              image={secondBarImage}
            />
          </ContentBox>

          <ContentBox
            styles={styles}
            title="Avstand fra ønsket tilstand"
            description="Dette diagrammet viser hvor mange kontroller pr. kategori som mangler et eller flere poeng fra at oppnådd tilstand er lik ønsket tilstand."
          >
            <DiagramImage imgHeight={242} imgWidth={484} image={barImage} />
          </ContentBox>
        </View>
        <Text
          style={styles.pageNumber}
          render={({ pageNumber, totalPages }) =>
            `${pageNumber} / ${totalPages}`
          }
          fixed
        />
      </Page>
      <Page style={styles.mpBody}>
        <ContentBox
          styles={styles}
          title="Viktigste tiltak"
          description={`Denne listen viser totalt de ${
            mpLength < 12 ? number[mpLength] : mpLength + 1
          } viktigste tiltakene basert på forskjellen mellom ønsket og oppnådd tilstand vektet med prioritet pr. kategori.`}
        >
          <View>
            {categories.map((category, index) => (
              <MostPressing
                key={index}
                header={`${category} (${results.categories[category].mostPressing.length} tiltak)`}
                results={results.categories[category]}
              />
            ))}
          </View>
        </ContentBox>
        <Text
          style={styles.pageNumber}
          render={({ pageNumber, totalPages }) =>
            `${pageNumber} / ${totalPages}`
          }
          fixed
        />
      </Page>
    </Document>
  );
};

export const DownloadPDF = ({
  results,
  categories,
  company,
  assessment,
  spiderImage,
  barImage,
  secondBarImage,
}: {
  results: ResultType | null;
  categories: string[];
  company: CompanyType;
  assessment: AssessmentType | null;
  spiderImage: string | undefined;
  barImage: string | undefined;
  secondBarImage: string | undefined;
}) => {
  if (!results || !assessment) {
    return null;
  } else {
    return (
      <div>
        <PDFDownloadLink
          document={
            <PDFCreate
              results={results}
              categories={categories}
              company={company}
              assessment={assessment}
              spiderImage={spiderImage}
              barImage={barImage}
              secondBarImage={secondBarImage}
            />
          }
          fileName={`Tilstandsanalyse - ${company.companyName} - ${format(
            new Date(),
            "dd MM yyyy"
          )}`}
        >
          {({ blob, url, loading, error }) =>
            loading ? (
              <Button>Last ned PDF</Button>
            ) : (
              <Button>Last ned PDF</Button>
            )
          }
        </PDFDownloadLink>
      </div>
    );
  }
};
