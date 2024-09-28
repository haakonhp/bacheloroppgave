import AssessmentType from "@/types/AssessmentType";
import { View, Text, Image, StyleSheet, Page } from "@react-pdf/renderer";
import React, { useEffect, useState } from "react";
import { Dunamis64Img } from "./DunamisLogo";
import { format } from "date-fns";
import { CompanyType } from "@/types/CompanyType";

const IntroPage = ({
  assessment,
  categories,
  company,
}: {
  assessment: AssessmentType;
  categories: string[];
  company: CompanyType;
}) => {
  const [sourcesMap, setSourcesMap] = useState(new Set());

  const styles = StyleSheet.create({
    body: {
      padding: 20,
      alignItems: "center",
      width: "210cm",
      justifyContent: "space-around",
    },
    title: {
      margin: 12,
      fontSize: 18,
      fontFamily: "Oswald",
      textTransform: "uppercase",
    },
    text: {
      margin: 12,
      fontSize: 16,
      textAlign: "justify",
      fontFamily: "Helvetica",
    },
    disclaimer: {
      fontSize: 12,
      textAlign: "justify",
      fontFamily: "Helvetica",
    },
    header: {
      width: 300,
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

  useEffect(() => {
    if (assessment) {
      // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set/add
      const uniqueSources = new Set();
      assessment.answers.forEach((item) =>
        uniqueSources.add(item.control.source)
      );

      setSourcesMap(uniqueSources);
    }
  }, [assessment]);

  return (
    <Page style={styles.body}>
      <Image style={styles.header} src={Dunamis64Img} />
      <Text style={styles.title}>Resultat Tilstandsanalyse</Text>

      <View style={{ backgroundColor: "#f9f9f9", width: 550 }}>
        <View style={{ backgroundColor: "#FFEBDA", width: 550 }}>
          <Text style={styles.title}>{company.companyName}</Text>
          <Text style={styles.text}>Analyse: {assessment.name}</Text>
        </View>

        <Text style={styles.text}>
          Denne tilstandsanalysen inkluderer {assessment.answerCount} kontroller
          fra{" "}
          {Array.from(sourcesMap).length > 1 ? "rammeverkene" : "rammeverket"}{" "}
          {Array.from(sourcesMap).join(", ")}. Analysen startet den{" "}
          {format(new Date(assessment.createdAt), "dd.MM.yyyy")} og den siste
          vurdering ble tatt{" "}
          {format(new Date(assessment.updatedAt), "dd.MM.yyyy")}.{" "}
          {assessment.completedAnswerCount} av {assessment.answerCount}{" "}
          kontroller er vurdert. Analysen tar med seg følgende kategorier i
          betraktning {categories.join(", ")}.
        </Text>
      </View>

      <View style={{ width: 550 }}>
        <View style={{ padding: 10, backgroundColor: "#FFEBDA" }}>
          <Text style={styles.disclaimer}>
            Dette dokumentet kan inneholde sensitiv informasjon om selskapets
            sårbarheter.
          </Text>
          <Text style={styles.disclaimer}>
            Vennligst beskytt dokumentet i henhold til dette.
          </Text>
        </View>
      </View>
      <Text
        style={styles.pageNumber}
        render={({ pageNumber, totalPages }) => `${pageNumber} / ${totalPages}`}
        fixed
      />
    </Page>
  );
};

export default IntroPage;
