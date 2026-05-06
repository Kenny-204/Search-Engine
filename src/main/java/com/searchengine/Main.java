package com.searchengine;


import com.searchengine.Indexing.Indexer;
import com.searchengine.documentprocessing.DocumentProcessor;
import com.searchengine.documentprocessing.services.Normalizer;
import com.searchengine.documentprocessing.services.Tokenizer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    static void main() {
        String text = "The meeting commenced with an opening prayer led by Mr. Ibrahim.\n" +
                "\n" +
                "1.  Updates from Mrs. Bolanle\n" +
                "•\tSignage change (Cashpoint to Payment Point): RFQ sent; vendors requested artwork before submitting quotations.\n" +
                "•\tBranded queue ropes (Arrival Hall): Purchase Order (P.O) awaiting approval.\n" +
                "•\tSmartphone for Head of AVSEC: P.O awaiting approval.\n" +
                "•\tUniform for Parking Services: RFQ sent; quotation received. Fabric sample requested.\n" +
                "•\tSouvenirs for Facility Tour: P.O awaiting approval.\n" +
                "•\tWastewater drainage fabrication (under AC units): P.O awaiting approval.\n" +
                "•\tOil and fuel filter: One quotation received. Other vendors requested chassis number; email sent to Operations Department.\n" +
                "•\tSearch mirror (user department): Item received.\n" +
                "•\tAC drainage: New request to be sent.\n" +
                "•\tFaulty Netgear switch: P.O awaiting approval.\n" +
                "•\tPurchase of AC for Rano Office: P.O awaiting approval. Concern raised regarding vendor.\n" +
                "•\tConveyor consumables: Inspection at basement store revealed several requested items already in stock.\n" +
                "\n" +
                "2.  Updates from Mrs. Ronke\n" +
                "•\tPSC stickers: P.O raised; awaiting approval.\n" +
                "•\tVehicle parts: Vendors inspected; quotations pending.\n" +
                "•\tUniform for cargo: Quotations received and under review. Mr. Frank advised requesting fabric samples.\n" +
                "•\tPaint for boardroom: Awaiting feedback. Engr. Moruf instructed that a reminder be sent.\n" +
                "•\tWalkie-talkies (Safety Department): P.O awaiting approval from Head of Safety.\n" +
                "•\tWalkie-talkies (Operations Department): P.O awaiting Authorised Signatory 1.\n" +
                "•\tUniform for Operations Department: Quotations received and under review. Mr. Frank advised requesting fabric samples.\n" +
                "•\tMarketing quota: Awaiting quotations.\n" +
                "•\tCivil consumables: Two quotations received (paint and consumables). Other vendors experiencing challenges. Store inspection scheduled.\n" +
                "•\tRouter: P.O and payment approved; awaiting delivery.\n" +
                "•\tPadlocks: RFQ sent.\n" +
                "•\tWaste bins: RFQ sent.\n" +
                "\n" +
                "Remark: Engr. Moruf instructed that delays from requesting departments should be followed up via email, and requests may be cancelled if there is no response.\n" +
                "\n" +
                "3.  Updates from Mr. Ibrahim\n" +
                "•\tDuct work: Sent for approval.\n" +
                "•\tChairs for IT (6 units): Sent for approval.\n" +
                "•\tPlumbing consumables: Discrepancies identified; request returned.\n" +
                "•\tCones and barriers (AVSEC — 10 cones, 3 barriers): Quotations received; awaiting inventory confirmation.\n" +
                "•\tFan fence: Inspection scheduled.\n" +
                "\n" +
                "Remark: Engr. Moruf advised exploring in-house execution where possible.\n" +
                "\n" +
                "4.  Updates from Mr. Frank\n" +
                "•\tChair repairs: Approved.\n" +
                "•\tSliding door repair: Completed; durability test ongoing before payment.\n" +
                "•\tExtractor pan: P.O awaiting Authorised Signatory 1.\n" +
                "•\tPurchase of 8 AC units: Cheques processed. Stands and materials supplied. Cabling ready; AC units pending.\n" +
                "•\tEngine oil (Operations): Awaiting Authorised Signatory 1; follow-up ongoing.\n" +
                "•\tTyres for operational vehicles: Quotations received; under analysis.\n" +
                "•\tApron repairs (selected areas): Vendor inspection completed.\n" +
                "•\tCobus and Hilux: One quotation received for Hilux; another pending. Vendors contacted for Cobus inspection.\n" +
                "•\tPneumatics: Engr. Moruf requested vendor recommendation.\n" +
                "\n" +
                "Remark: Engr. Moruf advised avoiding large vendors where possible due to higher costs.\n" +
                "\n" +
                "5.  Remarks from Engr. Moruf\n" +
                "•\tX-ray trays: Not approved.\n" +
                "•\tPneumatic sprayers (testing): Vendors advised to source from hydrostatic providers.\n" +
                "•\tScreening machine walkthrough metal detector: Not approved.\n" +
                "•\t100 kVA UPS: Not approved.\n" +
                "•\tFire detector: Not approved.\n" +
                "•\tCorrective maintenance (cable installation): Request cancelled; new quotations required.\n" +
                "•\tDiesel gauge system automation: P.O awaiting Authorised Signatory.\n" +
                "•\tCCTV upgrade: P.O completed; prices have increased.\n" +
                "•\tAVL bridge sensor maintenance: Quotations received.\n" +
                "•\tSliding door upgrade: One quotation received; two more pending.\n" +
                "•\tMSCP restroom renovation: One quotation received; two more pending.\n" +
                "•\tAdditional CCTV cameras: Ongoing.\n" +
                "\n" +
                "6.  Challenges\n" +
                "No challenges were reported by attendees.\n" +
                "\n" +
                "7.  Plans for the Week\n" +
                "•\tMrs. Bolanle: Follow up on purchase of AC for Rano Office; progress conveyor consumables.\n" +
                "•\tMr. Ibrahim: Follow up on cones and barriers for AVSEC; inspect and progress fan fence.\n" +
                "•\tMr. Frank: Progress purchase of AC units; follow up on Cobus and Hilux; handle pneumatics.\n" +
                "•\tEngr. Moruf: Follow up on MSCP restroom renovation; progress AVL bridge sensor maintenance.\n" +
                "\n" +
                "There being no further business, the meeting was adjourned.\n" +
                "\n" +
                "\n";
        var normalizer = new Normalizer();
        var tokenizer = new Tokenizer();
        var processor = new DocumentProcessor(normalizer,tokenizer);
        var indexer = new Indexer();

        InputStream stream =  new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
        var result = processor.process(stream,"file.txt");
        var invertedIndex = indexer.Index(result,"file.txt");

        for (var entry : invertedIndex.entrySet()) {
            System.out.println("Word: " + entry.getKey());
            for (var docEntry : entry.getValue().entrySet()) {
                System.out.println("  Doc: " + docEntry.getKey() + " Positions: " + docEntry.getValue());
            }
        }

    }
}
