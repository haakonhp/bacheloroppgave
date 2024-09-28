import { Answer } from "@/types/AssessmentType";

export const MockControls: Answer[] = [
  {
    id: "51ac8df3-6d16-468e-b8db-8deb98a8b804",
    control: {
      controlNumber: 1,
      details:
        "Identifiser virksomhetens strategi og prioriterte mål, samt regelverk, bransjenormer og avtaler som kan ha innvirkning på sikring av informasjonssystemer.",
      controlID: "1.1.1",
      controlOverview: "Identifiser virksomhetens strategi og prioriterte mål",
      controlQuestion:
        "Har virksomheten en klar og dokumentert strategi for informasjonssikkerhet som reflekterer organisasjonens overordnede mål og prioriteringer?\nEr det gjennomført en fullstendig gjennomgang av relevante regelverk, bransjenormer og avtaler for å sikre at virksomhetens informasjonssikkerhetspraksis er i samsvar med disse kravene?\nHvordan integreres virksomhetens strategi for informasjonssikkerhet med andre strategiske initiativer, og hvordan støtter dette virksomhetens overordnede mål?\nEr ansatte og ledere informert om de viktigste elementene i sikkerhetsstrategien, og hvordan påvirker dette deres daglige arbeid og beslutningstaking?\nFinnes det et opplegg for å revurdere og oppdatere sikkerhetsstrategien og tilhørende mål i lys av endringer i det eksterne miljøet, teknologiske fremskritt, eller etter innsikt fra sikkerhetsbrudd og hendelser?",
      priority: 4,
      source: "NSM GP-IKT 2.0 ver. 2023.12",
      maturityLevel0: "Ikke utført / ikke vurdert",
      maturityLevel1:
        "Ikke formalisert. Uformell kontroll. Ikke avklarte roller og ansvar. Kontrollaktivitetene er ad hoc basert, usystematisk og ikke dokumentert",
      maturityLevel2:
        "Formalisert, men ikke helhetlig Etablering og formidling av roller, ansvar og myndighet er formalisert, men ikke helhetlig. Dokumenterte rutiner er på plass.",
      maturityLevel3:
        "Formalisert i en hensiktsmessig og helhetlig prosess Kontrollsystem basert på risikovurdering. Dokumenterte kontroller med klart ansvar for gjennomføring. Tilstrekkelig kompetanse på internkontroll i hele virksomheten",
      maturityLevel4:
        "Forankret, etterlevd og integrert i arbeidsprosesser Etablering og formidling av roller, ansvar og myndighet er forankret, etterlevd, og integrert i relevante arbeidsprosesser. Strukturert oppfølging og løpende forbedring av internkontrollen",
      maturityLevel5:
        "Systematisert erfaringslæring som medfører kontinuerlig forbedring og endring Kontrollsystemet er integrert i helhetlig virksomhetsstyring (målstyring og kvalitetssystem). Kontinuerlig forbedring og endring. Arbeidet dokumenteres",
      cyberDefenseFunction: "1. Identifisere",
      cyberDefenseFunctionColor: "#5B9BD5",
      cyberDefenseAssetColor: "",
      cyberDefenseAsset: [],
      controlTypes: ["Prevantiv"],
      implementingFrameworks: [
        {
          frameworkReferenceListId: "a549c5ff-1c85-4997-b4d2-c22598a547c7",
          dbReferences: ["5.31", "5.32", "5.34"],
          framework: {
            frameworkNumber: 1,
            framework: "ISO 27002 v2022",
          },
        },
      ],
    },
    lastUpdatedAt: "2024-05-09T07:10:25.172Z",
    value: 1,
    desiredValue: 4,
  },
  {
    id: "272587f6-883c-4517-8e84-78ce34d060df",
    control: {
      controlNumber: 2,
      details:
        "Identifiser virksomhetens strukturer og prosesser for sikkerhetsstyring.  Dette inkluderer normalt a) policyer fra ledelsen, b) ledelsesstruktur med veldefinert ansvar og ansvarslinjer, c) prosesser for risikostyring (se 1.1.3) d) fastsatte toleransegrenser for risiko (se 1.1.4), e) tilføring av tilstrekkelige ressurser og fagkompetanse for å støtte ledelsen i arbeidet. \n\nf) Etabler strukturer og prosesser for sikkerhetsstyring dersom dette mangler. Sørg for at det tilpasses virksomheten og er en inkludert del av virksomhetsstyringen. Se «Utdypende informasjon» for ytterligere informasjon.",
      controlID: "1.1.2",
      controlOverview:
        "Identifiser virksomhetens strukturer og prosesser for sikkerhetsstyring.",
      controlQuestion: "",
      priority: 4,
      source: "NSM GP-IKT 2.0 ver. 2023.12",
      maturityLevel0: "Ikke utført / ikke vurdert",
      maturityLevel1:
        "Ikke formalisert. Uformell kontroll. Ikke avklarte roller og ansvar. Kontrollaktivitetene er ad hoc basert, usystematisk og ikke dokumentert",
      maturityLevel2:
        "Formalisert, men ikke helhetlig Etablering og formidling av roller, ansvar og myndighet er formalisert, men ikke helhetlig. Dokumenterte rutiner er på plass.",
      maturityLevel3:
        "Formalisert i en hensiktsmessig og helhetlig prosess Kontrollsystem basert på risikovurdering. Dokumenterte kontroller med klart ansvar for gjennomføring. Tilstrekkelig kompetanse på internkontroll i hele virksomheten",
      maturityLevel4:
        "Forankret, etterlevd og integrert i arbeidsprosesser Etablering og formidling av roller, ansvar og myndighet er forankret, etterlevd, og integrert i relevante arbeidsprosesser. Strukturert oppfølging og løpende forbedring av internkontrollen",
      maturityLevel5:
        "Systematisert erfaringslæring som medfører kontinuerlig forbedring og endring Kontrollsystemet er integrert i helhetlig virksomhetsstyring (målstyring og kvalitetssystem). Kontinuerlig forbedring og endring. Arbeidet dokumenteres",
      cyberDefenseFunction: "1. Identifisere",
      cyberDefenseFunctionColor: "#5B9BD5",
      cyberDefenseAssetColor: "",
      cyberDefenseAsset: [],
      controlTypes: ["Prevantiv"],
      implementingFrameworks: [
        {
          frameworkReferenceListId: "3e580cdc-2570-409a-9931-8dc6bc400f96",
          dbReferences: ["5.1", "5.2", "5.3", "5.4"],
          framework: {
            frameworkNumber: 1,
            framework: "ISO 27002 v2022",
          },
        },
      ],
    },
    lastUpdatedAt: "2024-04-03T13:54:54",
    value: 2,
    desiredValue: 3,
  },
  {
    id: "d7f6cc1e-7d95-48c4-ac23-427505bc281c",
    control: {
      controlNumber: 3,
      details:
        "Identifiser virksomhetens prosesser for risikostyring knyttet til IKT. Dette inkluderer normalt a) verdivurdering, b) trusselvurdering, c) kartlegge eksisterende sikkerhetstiltak, d) risikoidentifisering, e) risikovurdering, f) risikorapportering, g) risikohåndtering, h) etablere eller justere sikkerhetstiltak for å redusere risiko i) verifisere at sikkerhetstiltakene fungerer etter hensikt.\n\nj) Etabler prosesser for risikostyring dersom dette mangler. Sørg for at prosessene tilpasses virksomheten og er en inkludert del av virksomhetsstyringen og sikkerhetsstyringen. Se «Utdypende informasjon» for ytterligere informasjon.",
      controlID: "1.1.3",
      controlOverview:
        "Identifiser virksomhetens prosesser for risikostyring knyttet til IKT",
      controlQuestion: "",
      priority: 4,
      source: "NSM GP-IKT 2.0 ver. 2023.12",
      maturityLevel0: "Ikke utført / ikke vurdert",
      maturityLevel1:
        "Ikke formalisert. Uformell kontroll. Ikke avklarte roller og ansvar. Kontrollaktivitetene er ad hoc basert, usystematisk og ikke dokumentert",
      maturityLevel2:
        "Formalisert, men ikke helhetlig Etablering og formidling av roller, ansvar og myndighet er formalisert, men ikke helhetlig. Dokumenterte rutiner er på plass.",
      maturityLevel3:
        "Formalisert i en hensiktsmessig og helhetlig prosess Kontrollsystem basert på risikovurdering. Dokumenterte kontroller med klart ansvar for gjennomføring. Tilstrekkelig kompetanse på internkontroll i hele virksomheten",
      maturityLevel4:
        "Forankret, etterlevd og integrert i arbeidsprosesser Etablering og formidling av roller, ansvar og myndighet er forankret, etterlevd, og integrert i relevante arbeidsprosesser. Strukturert oppfølging og løpende forbedring av internkontrollen",
      maturityLevel5:
        "Systematisert erfaringslæring som medfører kontinuerlig forbedring og endring Kontrollsystemet er integrert i helhetlig virksomhetsstyring (målstyring og kvalitetssystem). Kontinuerlig forbedring og endring. Arbeidet dokumenteres",
      cyberDefenseFunction: "1. Identifisere",
      cyberDefenseFunctionColor: "#5B9BD5",
      cyberDefenseAssetColor: "",
      cyberDefenseAsset: [],
      controlTypes: ["Preventiv", "Korrigerende"],
      implementingFrameworks: [
        {
          frameworkReferenceListId: "26f50a14-45a7-4202-929d-bbfb0eac79ee",
          dbReferences: ["5.1", "5.29", "5.36", "8.8"],
          framework: {
            frameworkNumber: 1,
            framework: "ISO 27002 v2022",
          },
        },
      ],
    },
    lastUpdatedAt: "2024-04-03T13:54:56",
    value: 2,
    desiredValue: 5,
  },
];
