import ControlType from "@/types/ControlType";
import { View, Text, StyleSheet } from "@react-pdf/renderer";

export const MostPressing = ({
  header,
  results,
}: {
  header: string;
  results: any;
}) => {
  const styles = StyleSheet.create({
    header: {
      fontSize: 12,
      padding: 10,
      marginBottom: 10,
      fontFamily: "Oswald",
      color: "white",
      backgroundColor: results.mostPressing[0]?.control
        ?.cyberDefenseFunctionColor
        ? results.mostPressing[0].control.cyberDefenseFunctionColor
        : "#808080",
    },

    text: {
      alignItems: "center",
      margin: 3,
      fontSize: 10,
      fontFamily: "Helvetica",
    },
    boldtext: {
      alignItems: "center",
      margin: 3,
      fontSize: 12,
      fontFamily: "Oswald",
    },
    box: {
      alignContent: "center",
      alignItems: "center",
    },
    itemOdd: {
      marginBottom: 10,
      paddingBottom: 10,
      borderRadius: 5,
      flexDirection: "row",
      alignContent: "center",
      alignItems: "center",
      backgroundColor: "#FFFFFF",
      width: "100%",
    },
    itemEven: {
      marginBottom: 10,
      paddingBottom: 10,
      borderRadius: 5,
      flexDirection: "row",
      alignContent: "center",
      alignItems: "center",
      backgroundColor: "#cedff0",
      width: "100%",
    },
    information: {
      flexDirection: "row",
      width: "100%",
    },
  });
  return (
    <>
      <View style={{ width: 550 }}>
        <Text wrap={false} style={styles.header}>
          {header}
        </Text>
        <View style={styles.box}>
          {results &&
            results.mostPressing.map(
              (
                item: {
                  control: ControlType;
                  value: number;
                  desiredValue: number;
                },
                index: any
              ) => (
                <View
                  wrap={false}
                  key={index}
                  style={[index % 2 === 0 ? styles.itemEven : styles.itemOdd]}
                >
                  <View style={{ padding: 10, fontWeight: "bold" }}>
                    <Text style={[styles.text, { alignContent: "center" }]}>
                      {index + 1}
                    </Text>
                  </View>
                  <View
                    style={{ flexDirection: "column", alignContent: "center" }}
                  >
                    <Text style={styles.boldtext}>
                      {item.control.controlOverview}
                    </Text>
                    <View style={styles.information}>
                      <Text style={styles.text}>
                        Kontrollnummer: {item.control.controlID} |
                      </Text>
                      <Text style={styles.text}>
                        Kilde: {item.control.source} |
                      </Text>
                      <Text style={styles.text}>
                        Måloppnåelse: {item.value} / {item.desiredValue}
                      </Text>
                    </View>
                  </View>
                </View>
              )
            )}
        </View>
      </View>
    </>
  );
};
