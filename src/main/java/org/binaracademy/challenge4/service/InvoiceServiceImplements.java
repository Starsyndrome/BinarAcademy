package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.binaracademy.challenge4.model.response.OrderDetailJasper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class InvoiceServiceImplements implements InvoiceService{
    /*
    Maaf ya kak literally copy-paste, puyeng bat asli sama jasper report ini ya Allah
     */
    @Override
    public byte[] generateInvoice(String username) throws FileNotFoundException, JRException {
        log.info("Generating invoice for: {}", username);
        List<OrderDetailJasper> orderDetailJaspers = Arrays.asList(
                OrderDetailJasper.builder().productName("Cappuccino").price("Rp. 20.000").quantity(2L).build(),
                OrderDetailJasper.builder().productName("Croissant").price("Rp. 17.000").quantity(1L).build(),
                OrderDetailJasper.builder().productName("Red Velvet").price("Rp. 20.000").quantity(1L).build(),
                OrderDetailJasper.builder().productName("Lemon Tea").price("Rp. 12.000").quantity(5L).build(),
                OrderDetailJasper.builder().productName("Croissant").price("Rp. 17.000").quantity(2L).build()
        );

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("username", username);
        parameterMap.put("finalPrice", "Rp. 171.000");
        parameterMap.put("orderDetailJasper", orderDetailJaspers);
        JasperPrint invoice = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:invoice_v2.jrxml").getAbsolutePath()),
                parameterMap,
                new JRBeanCollectionDataSource(orderDetailJaspers)
        );

        return JasperExportManager.exportReportToPdf(invoice);
    }
}
