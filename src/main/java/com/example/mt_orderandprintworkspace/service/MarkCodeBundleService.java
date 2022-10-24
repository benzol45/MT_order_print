package com.example.mt_orderandprintworkspace.service;

import com.example.mt_orderandprintworkspace.DTO.LabelDTO;
import com.example.mt_orderandprintworkspace.entity.MarkCodeBundle;
import com.example.mt_orderandprintworkspace.entity.Order;
import com.example.mt_orderandprintworkspace.repository.MarkCodeBundleRepository;
import com.example.mt_orderandprintworkspace.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarkCodeBundleService {
    private final MarkCodeBundleRepository markCodeBundleRepository;

    @Autowired
    public MarkCodeBundleService(MarkCodeBundleRepository markCodeBundleRepository) {
        this.markCodeBundleRepository = markCodeBundleRepository;
    }

    /*public void test_save(Long id) {
        List<String> stringList = new ArrayList<>();
        Order order = orderRepository.findById(id).get();
        for (int i = 0; i<order.getQuantity(); i++) {
            stringList.add("[01]03245676787719[21]5cKrbg[93]dGVz");
        }
        MarkCodeBundle markCodeBundle = new MarkCodeBundle();
        markCodeBundle.setOrder(order);
        markCodeBundle.setCodeBundle(stringList);
        markCodeBundleRepository.save(markCodeBundle);
    }

    public void test_read() {
        MarkCodeBundle markCodeBundle = markCodeBundleRepository.findById(1l).get();
        List<String> stringList = markCodeBundle.getCodeBundle();
        System.out.println(stringList.size());
        String first = stringList.get(0);
        for (String other: stringList) {
            if (!first.equals(other)) {
                System.out.println("All strings aren't same");
            }
        }
    }*/

    public List<MarkCodeBundle> getAll() {
        return markCodeBundleRepository.findAll();
    }

    public List<MarkCodeBundle> getAllActive() {
        //TODO сменить на выбор только активных
        return markCodeBundleRepository.findAll();
    }

    public MarkCodeBundle findById(long id) {
        Optional<MarkCodeBundle> markCodeBundle = markCodeBundleRepository.findById(id);
        if (!markCodeBundle.isPresent()) {
            throw new IllegalStateException("Can't find bundle with id " + id);
        }

        return markCodeBundle.get();
    }

    public byte[] getMarkCodeBundleAsPDFFile(long id) {
        MarkCodeBundle markCodeBundle = findById(id);
        List<String> markCodeList = markCodeBundle.getCodeBundle();
        String productName = markCodeBundle.getOrder().getProduct().getName();
        List<LabelDTO> labelDTOs = new ArrayList<>();
        for (String markCode: markCodeList) {
            labelDTOs.add(new LabelDTO(BarCodeDataMatrixGenerator.generateBarCodeDataMatrix(markCode), productName,MarkCodeUtil.getSerialNumber(markCode)));
        }

        return LabelPDFGenerator.generateLabelsPDF_size30x20(labelDTOs);
    }
}
