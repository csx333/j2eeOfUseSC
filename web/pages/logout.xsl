<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!--  从根开始遍历  -->
    <xsl:template match="/">
        <html>
            <head>
                <xsl:value-of select="view/header/title"/>
            </head>
            <body>
                <form>
                    <xsl:attribute name="name">
                        <xsl:value-of select="view/body/form/name" />
                    </xsl:attribute>
                    <xsl:attribute name="action">
                        <xsl:value-of select="view/body/form/action" />
                    </xsl:attribute>
                    <xsl:attribute name="method">
                        <xsl:value-of select="view/body/form/method" />
                    </xsl:attribute>
                    <xsl:for-each select="view/body/form/textView">
                        <label>
                            <xsl:attribute name="for">
                                <xsl:value-of select="name" />
                            </xsl:attribute>
                            <xsl:value-of select="label"/>
                        </label>
                        <input>
                            <xsl:attribute name="name">
                                <xsl:value-of select="name" />
                            </xsl:attribute>
                            <xsl:attribute name="id">
                                <xsl:value-of select="name" />
                            </xsl:attribute>
                            <xsl:attribute name="type">text</xsl:attribute>
                            <xsl:attribute name="value">
                                <xsl:value-of select="value" />
                            </xsl:attribute>
                        </input>
                        <br/>
                    </xsl:for-each>

                    <xsl:for-each select="view/body/form/buttonView">
                        <label>
                            <xsl:attribute name="for">
                                <xsl:value-of select="name" />
                            </xsl:attribute>
                            <xsl:value-of select="label"/>
                        </label>
                        <input>
                            <xsl:attribute name="name">
                                <xsl:value-of select="name" />
                            </xsl:attribute>
                            <xsl:attribute name="id">
                                <xsl:value-of select="name" />
                            </xsl:attribute>
                            <xsl:attribute name="type">submit</xsl:attribute>
                        </input>
                    </xsl:for-each>
                </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>