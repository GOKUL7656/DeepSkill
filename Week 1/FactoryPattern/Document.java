interface Document {
    void open();
    void save();
    void close();
}

class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word Document");
    }

    @Override
    public void save() {
        System.out.println("Saving Word Document");
    }

    @Override
    public void close() {
        System.out.println("Closing Word Document");
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF Document");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF Document");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF Document");
    }
}

class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel Documen");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel Document");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        System.out.println("Creating a new Word Document");
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        System.out.println("Creating a new PDF Document");
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        System.out.println("Creating a new Excel Document");
        return new ExcelDocument();
    }
}
