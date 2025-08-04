public class FactoryPatternMethod {
    public static void main(String[] args) {
        System.out.println("--- Word Document Creation ---");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();
        System.out.println();

        System.out.println("--- PDF Document Creation ---");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();
        System.out.println();

        System.out.println("--- Excel Document Creation ---");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();
        System.out.println();
    }
}
