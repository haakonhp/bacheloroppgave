package dtos;

import configurations.Translations;
import converters.SplitOnLineBreakNoNullConverter;
import converters.ImplementingFrameworkConverter;
import converters.SourceConverter;
import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import hiof.gruppe25.core.models.framework.Framework;
import hiof.gruppe25.core.models.frameworkreference.FrameworkReference;

import java.util.List;
public class ControlDTO {
    @CsvBindByName(column = Translations.ControlNumber)
    private int controlNumber;
    @CsvBindByName(column = Translations.Details)
    private String details;
    @CsvBindByName(column = Translations.controlID)
    private String controlID;
    @CsvBindByName(column = Translations.controlOverview)
    private String controlOverview;
    @CsvBindByName(column = Translations.controlQuestion)
    private String controlQuestion;
    @CsvBindByName(column = Translations.Priority)
    private int priority;
    @CsvCustomBindByName(column = Translations.Source, converter = SourceConverter.class)
    private String source;
    @CsvBindByName(column = Translations.MaturityLevel0)
    private String maturityLevel0;
    @CsvBindByName(column = Translations.MaturityLevel1)
    private String maturityLevel1;
    @CsvBindByName(column = Translations.MaturityLevel2)
    private String maturityLevel2;
    @CsvBindByName(column = Translations.MaturityLevel3)
    private String maturityLevel3;
    @CsvBindByName(column = Translations.MaturityLevel4)
    private String maturityLevel4;
    @CsvBindByName(column = Translations.MaturityLevel5)
    private String maturityLevel5;
    @CsvBindByName(column = Translations.CyberDefenseFunction)
    private String cyberDefenseFunction;
    @CsvBindAndSplitByName(column = Translations.CyberDefenseAsset, splitOn = "\n", elementType = String.class, converter = SplitOnLineBreakNoNullConverter.class)
    private List<String> cyberDefenseAsset;
    @CsvBindAndSplitByName(column = Translations.controlTypes, splitOn = "\n", elementType = String.class, converter = SplitOnLineBreakNoNullConverter.class)
    private List<String> controlTypes;
    @CsvBindAndSplitByName(column = Translations.implementingFrameworks, splitOn = "\\|", elementType = Framework.class, converter = ImplementingFrameworkConverter.class)
    private List<FrameworkReference> implementingFrameworks;

    @CsvBindByName(column = Translations.CyberDefenseFunctionColor)
    private String cyberDefenseFunctionColor;
    @CsvBindByName(column = Translations.CyberDefenseAssetColor)
    private String cyberDefenseAssetColor;
}
