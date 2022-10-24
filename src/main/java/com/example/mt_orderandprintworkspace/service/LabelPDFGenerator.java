package com.example.mt_orderandprintworkspace.service;

import com.example.mt_orderandprintworkspace.DTO.LabelDTO;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LabelPDFGenerator {
    //Общая концепция вывода в PDF https://www.baeldung.com/thymeleaf-generate-pdf

    public static byte[] generateLabelsPDF_size30x20(List<LabelDTO> labelDTOs) {

        //-------------START GENERATE AS HTML----------------
        //Создания своего объекта ThymeLeaf https://stackoverflow.com/questions/46962612/get-raw-html-from-the-controller-spring-thymeleaf
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        templateResolver.setForceTemplateMode(true);

        templateEngine.setTemplateResolver(templateResolver);

        Context ctx = new Context();
        //Показать картинку на странице можно перекодировав её в base64 https://stackoverflow.com/questions/33045743/showing-base64string-image-with-thymeleaf
        ctx.setVariable("images", labelDTOs);
        final String htmlPage = templateEngine.process("label30x20", ctx);
        //System.out.println("result in html:" + htmlPage);

        //-------------START GENERATE PDF FROM HTML----------------
        // В примере очень старая версия iText, надо новее https://stackoverflow.com/questions/48422409/how-to-set-pdf-page-size-a4-when-we-use-itextrenderer-to-generate-pdf-from-thyme
        //Рассчет размера этикетки в точках:
        //A4 = 210 × 297 мм = 595 x 842 dot
        // 1 мм = 2.835 мм.
        // 30х20 = 85х56 - минимальный типовой размер термоэтикетки
        InputStream stream = new ByteArrayInputStream(htmlPage.getBytes());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfDocument pdf = new PdfDocument(new PdfWriter(byteArrayOutputStream));
        pdf.setDefaultPageSize(new PageSize(85,56));

        ConverterProperties converterProperties = new ConverterProperties();
        FontProvider dfp = new DefaultFontProvider(true, true, true);
        converterProperties.setFontProvider(dfp);

        //Не можем просто использовать HtmlConverter.convertToPdf(stream, pdf, converterProperties); т.к. использует стандартный шаблон без полей

        //Как установить поля у пдф https://stackoverflow.com/questions/54450119/itext-pdfhtml-set-margins
        Document document = new Document(pdf);
        document.setMargins(0,0,0,0);
        List<IElement> list;
        try {
            list = HtmlConverter.convertToElements(stream, converterProperties);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        for (IElement element : list) {
            if (element instanceof IBlockElement) {
                document.add((IBlockElement) element);
            }
        }
        document.close();

        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return byteArrayOutputStream.toByteArray();
    }
}
