<xsl:stylesheet version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:transform="/WEB-INF/transform.tld" >

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
         <xsl:for-each select="EMPTY_BASKET_NAME" >
         <xsl:variable name="emptyBasketText" select="EMPTY_BASKET_TEXT" />
         
         
<table class="table" >

  <tr>
    <td width="51%">Text:</td>
    <td width="49%">
       <textarea class="text" name="%= BasketData.TEXT %" rows="10" 
          cols="48" wrap="virtual" >
         <xsl:if test="not($emptyBasketText)" >
            Your basket is empty.<p/>
            Please add something to your basket before you checking out.<p/>
            Thank You<p/>
         </xsl:if>
         <xsl:if test="$emptyBasketText" >
            <xsl:value-of select="$emptyBasketText" disable-output-escaping="yes"/>
         </xsl:if>            
       </textarea>
    </td>
  </tr>

</table>
            </xsl:for-each>
         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>