import { View, Text } from "@react-pdf/renderer";
import React from "react";

const ContentBox = ({
  title,
  description,
  children,
  styles,
}: {
  title: string;
  description: string;
  children: React.ReactNode;
  styles: any;
}) => {
  return (
    <View style={{ width: 550 }}>
      <Text style={[styles.title, { backgroundColor: "#FFEBDA", padding: 5 }]}>
        {title}
      </Text>
      <View
        style={{
          backgroundColor: "#f9f9f9",
          padding: 5,
          marginBottom: 5,
        }}
      >
        <Text style={styles.text}>{description}</Text>
        <View
          style={{
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          {children}
        </View>
      </View>
    </View>
  );
};

export default ContentBox;
