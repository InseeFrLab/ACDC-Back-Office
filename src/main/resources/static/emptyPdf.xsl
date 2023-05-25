<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="EmptyPdf">
                    <fo:region-body margin="30mm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="EmptyPdf">
                <fo:flow flow-name="xsl-region-body">

                    <!-- Header -->
                    <fo:block font-size="14pt" font-weight="bold" text-align="center" space-after="20pt">
                        Sample Pdf File
                    </fo:block>
                    <fo:block font-size="12pt" font-weight="bold">
                        Date: <xsl:value-of select="emptyPdf/date"/>
                    </fo:block>


                    <!-- Customer Information -->
                    <fo:block font-size="14pt" space-after="20pt">
                        <fo:block font-weight="bold">Content</fo:block>
                    </fo:block>


                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
