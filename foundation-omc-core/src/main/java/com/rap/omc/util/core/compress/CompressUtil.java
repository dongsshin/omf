package com.rap.omc.util.core.compress;

//import devonframe.util.DateUtil;


/*     */ import java.io.File;

import com.rap.omc.util.core.DateUtil;
import com.rap.omc.util.core.FileUtil;
import com.rap.omc.util.core.compress.archive.Archive;
import com.rap.omc.util.core.compress.archive.TarArchive;
import com.rap.omc.util.core.compress.archive.ZipArchive;
import com.rap.omc.util.core.compress.compress.BZip2Compress;
import com.rap.omc.util.core.compress.compress.Compress;
import com.rap.omc.util.core.compress.compress.GZipCompress;

/*     */
/*     */ public class CompressUtil
/*     */ {

    /*     */ public static final String ZIP = "zip";

    /*     */ public static final String BZIP2 = "bzip2";

    /*     */ public static final String GZIP = "gz";

    /*     */
    /*     */ public String compress(String inputFilePath, String compressedFilePath)
    /*     */ {
        /* 116 */ return compress(inputFilePath, compressedFilePath, "zip");
        /*     */ }

    /*     */
    /*     */ public String compress(String inputFilePath, String compressedFilePath, String mode)
    /*     */ {
        /* 129 */ return compress(inputFilePath, compressedFilePath, mode, true);
        /*     */ }

    /*     */
    /*     */ public static String compress(String inputFilePath, String compressedFilePath, String archiveType,
            boolean overwrite)
    /*     */ {
        /* 143 */ File result = compress(new File(inputFilePath), new File(compressedFilePath), archiveType, overwrite,
                "UTF-8");
        /* 144 */ return result.getAbsolutePath();
        /*     */ }

    /*     */
    /*     */ public static String compress(String inputFilePath, String compressedFilePath, String archiveType,
            boolean overwrite, String encodingType)
    /*     */ {
        /* 157 */ File result = compress(new File(inputFilePath), new File(compressedFilePath), archiveType, overwrite,
                encodingType);
        /* 158 */ return result.getAbsolutePath();
        /*     */ }

    /*     */
    /*     */ public File compress(File inputFile, File compressedFile)
    /*     */ {
        /* 171 */ return compress(inputFile, compressedFile, "zip");
        /*     */ }

    /*     */
    /*     */ public File compress(File inputFile, File compressedFile, String archiveType)
    /*     */ {
        /* 184 */ return compress(inputFile, compressedFile, archiveType, true, "UTF-8");
        /*     */ }

    /*     */
    /*     */ public static File compress(File inputFile, File compressedFile, String archiveType, boolean overwrite, String encodingType)
/*     */   {
/* 198 */     Archive archive = null;
/* 199 */     Compress compress = null;
/*     */ 
/* 201 */     File resultFile = null;
/* 202 */     File archivedResult = null;
/* 203 */     File newCompressedFile = null;
/*     */ 
/* 205 */     if ((!(overwrite)) && (compressedFile.exists()))
/* 206 */       newCompressedFile = createNewFile(compressedFile);
/*     */     else {
/* 208 */       newCompressedFile = compressedFile;
/*     */     }
/*     */ 
/* 211 */     if (archiveType == "zip") {
/* 212 */       if (!(compressedFile.getName().toLowerCase().endsWith(".zip"))) throw new IllegalArgumentException("The file name should end with zip.");
/* 213 */       archive = new ZipArchive();
/* 214 */       resultFile = archive.archive(inputFile, newCompressedFile, encodingType);
/*     */     }
/* 216 */     else if (archiveType == "gz") {
/* 217 */       File archivedFile = null;
/*     */       try {
/* 219 */         String fileNameWithExt = newCompressedFile.getName();
/* 220 */         if (!(fileNameWithExt.toLowerCase().endsWith(".tar.gz"))) throw new IllegalArgumentException("The file name should end with tar.gz.");
/* 221 */         String archivedFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".tar.gz"));
/* 222 */         String archivedFilePath = compressedFile.getParent() + File.separator + archivedFileName + ".tar";
/* 223 */         archivedFile = new File(archivedFilePath);
/*     */ 
/* 226 */         archive = new TarArchive();
/* 227 */         archivedResult = archive.archive(inputFile, archivedFile, encodingType);
/*     */ 
/* 230 */         if (archivedResult.exists()) {
/* 231 */           compress = new GZipCompress();
/* 232 */           resultFile = compress.compress(archivedFile, newCompressedFile);
/* 233 *		            }
/* 234 */         throw new IllegalStateException("The archived file cannot be found.");
/*     */       }
                }
/*     */       finally {
/* 237 */         if ((archivedResult != null) && (archivedResult.exists()))
/* 238 */           archivedResult.delete();
/*     */       }
/* 237 */       if ((archivedResult != null) && (archivedResult.exists())) {
/* 238 */         archivedResult.delete();
/*     */       }
/*     */     }
/* 241 */     else if (archiveType == "bzip2") {
/* 242 */       File archivedFile = null;
/*     */       try
/*     */       {
/* 245 */         String fileNameWithExt = newCompressedFile.getName();
/* 246 */         if (!(fileNameWithExt.toLowerCase().endsWith(".tar.bz2"))) throw new IllegalArgumentException("The file name should end with tar.bz2.");
/*     */ 
/* 248 */         String archivedFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".tar.bz2"));
/* 249 */         String archivedFilePath = compressedFile.getParent() + File.separator + archivedFileName + ".tar";
/* 250 */         archivedFile = new File(archivedFilePath);
/*     */ 
/* 253 */         archive = new TarArchive();
/* 254 */         archivedResult = archive.archive(inputFile, archivedFile, encodingType);
/*     */ 
/* 257 */         if (archivedResult.exists()) {
/* 258 */           compress = new BZip2Compress();
/* 259 */           resultFile = compress.compress(archivedFile, newCompressedFile);
/* 260 */           return resultFile;
}
/* 261 */         throw new IllegalStateException("The archived file cannot be found.");
/*     */       }
/*     */       finally {
/* 264 */         if ((archivedResult != null) && (archivedResult.exists()))
/* 265 */           archivedResult.delete();
/*     */       }

/*     */     }
/*     */ 
/* 269 */     return resultFile;
/*     */   }

    /*     */
    /*     */ public static File compress(File[] inputFiles, File compressedFile)
    /*     */ {
        /* 282 */ return compress(inputFiles, compressedFile, "zip");
        /*     */ }

    /*     */
    /*     */ public static File compress(File[] inputFiles, File compressedFile, String mode)
    /*     */ {
        /* 295 */ return compress(inputFiles, compressedFile, mode, true);
        /*     */ }

    /*     */
    /*     */ public static File compress(File[] inputFiles, File compressedFile, String mode, boolean overwrite)
    /*     */ {
        /* 309 */ return compress(inputFiles, compressedFile, mode, false, "UTF-8");
        /*     */ }

    /*     */
    /*     */ public static File compress(File[] inputFiles, File compressedFile, String mode, boolean overwrite, String encodingType)
/*     */   {
/* 322 */     Archive archive = null;
/* 323 */     Compress compress = null;
/*     */ 
/* 325 */     File resultFile = null;
/* 326 */     File archivedResult = null;
/* 327 */     File newCompressedFile = null;
/*     */ 
/* 329 */     if ((!(overwrite)) && (compressedFile.exists()))
/* 330 */       newCompressedFile = createNewFile(compressedFile);
/*     */     else {
/* 332 */       newCompressedFile = compressedFile;
/*     */     }
/*     */ 
/* 335 */     if (mode == "zip") {
/* 336 */       if (!(newCompressedFile.getName().toLowerCase().endsWith(".zip"))) throw new IllegalArgumentException("The file name should end with zip.");
/* 337 */       archive = new ZipArchive();
/* 338 */       label286: label498: resultFile = archive.archive(inputFiles, newCompressedFile, encodingType);
/*     */     }
/* 340 */     else if (mode == "gz") {
/* 341 */       File archivedFile = null;
/*     */       try {
/* 343 */         String fileNameWithExt = newCompressedFile.getName();
/*     */ 
/* 345 */         if (!(fileNameWithExt.toLowerCase().endsWith(".tar.gz"))) throw new IllegalArgumentException("The file name should end with tar.gz.");
/* 346 */         String archivedFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".tar.gz"));
/* 347 */         String archivedFilePath = compressedFile.getParent() + File.separator + archivedFileName + ".tar";
/* 348 */         archivedFile = new File(archivedFilePath);
/*     */ 
/* 351 */         archive = new TarArchive();
/* 352 */         archivedResult = archive.archive(inputFiles, archivedFile, encodingType);
/*     */ 
/* 355 */         if (archivedResult.exists()) {
/* 356 */           compress = new GZipCompress();
/* 357 */           resultFile = compress.compress(archivedFile, newCompressedFile);
/* 358 */           }
/* 359 */         throw new IllegalStateException("The archived file cannot be found.");
/*     */       }
/*     */       finally {
/* 362 */         if ((archivedResult != null) && (archivedResult.exists()))
/* 363 */           archivedResult.delete();
/*     */       }

/*     */ 
/*     */     }
/* 367 */     else if (mode == "bzip2") {
/* 368 */       File archivedFile = null;
/*     */       try {
/* 370 */         String fileNameWithExt = newCompressedFile.getName();
/* 371 */         if (!(fileNameWithExt.toLowerCase().endsWith(".tar.bz2"))) throw new IllegalArgumentException("The file name should end with tar.bz2.");
/* 372 */         String archivedFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".tar.bz2"));
/* 373 */         String archivedFilePath = compressedFile.getParent() + File.separator + archivedFileName + ".tar";
/* 374 */         archivedFile = new File(archivedFilePath);
/*     */ 
/* 377 */         archive = new TarArchive();
/* 378 */         archivedResult = archive.archive(inputFiles, archivedFile, encodingType);
/*     */ 
/* 381 */         if (archivedResult.exists()) {
/* 382 */           compress = new BZip2Compress();
/* 383 */           resultFile = compress.compress(archivedFile, newCompressedFile);
/* 384 */           }
/* 385 */         throw new IllegalStateException("The archived file cannot be found.");
/*     */       }
/*     */       finally {
/* 388 */         if ((archivedResult != null) && (archivedResult.exists()))
/* 389 */           archivedResult.delete();
/*     */       }

/*     */     }
/*     */ 
/* 393 */     return resultFile;
/*     */   }

    /*     */
    /*     */ public static String[] extract(String compressedFilePath, String outputFilePath)
    /*     */ {
        /* 405 */ return extract(compressedFilePath, outputFilePath, "zip");
        /*     */ }

    /*     */
    /*     */ public static String[] extract(String compressedFilePath, String outputFilePath, String mode)
    /*     */ {
        /* 418 */ return extract(compressedFilePath, outputFilePath, mode, false);
        /*     */ }

    /*     */
    /*     */ public static String[] extract(String compressedFilePath, String outputFilePath, String mode,
            boolean overwrite)
    /*     */ {
        /* 432 */ File[] files = extract(new File(compressedFilePath), new File(outputFilePath), mode, overwrite,
                "UTF-8");
        /*     */
        /* 434 */ String[] fileStringArray = new String[files.length];
        /* 435 */ for (int inx = 0; inx < files.length; ++inx) {
            /* 436 */ fileStringArray[inx] = files[inx].getAbsolutePath();
            /*     */ }
        /* 438 */ return fileStringArray;
        /*     */ }

    /*     */
    /*     */ public static File[] extract(File compressedFile, File outputFile)
    /*     */ {
        /* 450 */ return extract(compressedFile, outputFile, "zip");
        /*     */ }

    /*     */
    /*     */ public static File[] extract(File compressedFile, File outputFile, String mode)
    /*     */ {
        /* 463 */ return extract(compressedFile, outputFile, mode, false);
        /*     */ }

    /*     */
    /*     */ public static File[] extract(File compressedFile, File outputFile, String mode, boolean overwrite)
    /*     */ {
        /* 476 */ return extract(compressedFile, outputFile, mode, false, "UTF-8");
        /*     */ }

    /*     */
    /*     */ public static File[] extract(File compressedFile, File outputFile, String mode, boolean overwrite, String encodingType)
/*     */   {
/* 489 */     Archive archive = null;
/* 490 */     Compress compress = null;
/*     */ 
/* 492 */     File[] resultList = null;
/* 493 */     File decompressedResult = null;
/*     */ 
/* 495 */     if (mode == "zip") {
/* 496 */       if (!(compressedFile.getName().toLowerCase().endsWith(".zip"))) throw new IllegalArgumentException("The file name should end with zip.");
/* 497 */       archive = new ZipArchive();
/* 498 */       label528: label287: resultList = archive.unarchive(compressedFile, outputFile, encodingType);
/*     */     }
/* 500 */     else if (mode == "gz") {
/* 501 */       if (!(compressedFile.getName().toLowerCase().endsWith(".gz"))) throw new IllegalArgumentException("The file name should end with gz.");
/* 502 */       File decompressedFile = null;
/*     */       try {
/* 504 */         String fileNameWithExt = compressedFile.getName();
/* 505 */         if (!(fileNameWithExt.toLowerCase().endsWith(".tar.gz"))) throw new IllegalArgumentException("The file name should end with tar.gz.");
/* 506 */         String decompressedFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".tar.gz"));
/* 507 */         String decompressedFilePath = compressedFile.getParent().replace('\\', '/') + "/" + decompressedFileName + ".tar";
/* 508 */         decompressedFile = new File(decompressedFilePath);
/*     */ 
/* 511 */         compress = new GZipCompress();
/* 512 */         decompressedResult = compress.decompress(compressedFile, decompressedFile);
/*     */ 
/* 515 */         if (decompressedResult.exists()) {
/* 516 */           archive = new TarArchive();
/* 517 */           resultList = archive.unarchive(decompressedFile, outputFile, encodingType);
 }
/* 519 */         throw new IllegalStateException("The decompressed file cannot be found.");
/*     */       }
/*     */       finally
/*     */       {
/* 523 */         if ((decompressedResult != null) && (decompressedResult.exists()))
/* 524 */           decompressedResult.delete();
/*     */       }

/*     */     }
/* 527 */     else if (mode == "bzip2") {
/* 528 */       if (!(compressedFile.getName().toLowerCase().endsWith(".bz2"))) throw new IllegalArgumentException("The file name should end with bz2.");
/* 529 */       File decompressedFile = null;
/*     */       try {
/* 531 */         String fileNameWithExt = compressedFile.getName();
/* 532 */         if (!(fileNameWithExt.toLowerCase().endsWith(".tar.bz2"))) throw new IllegalArgumentException("The file name should end with tar.bz2.");
/*     */ 
/* 534 */         String archivedFileName = fileNameWithExt.substring(0, fileNameWithExt.indexOf(".tar.bz2"));
/* 535 */         String archivedFilePath = compressedFile.getParent().replace('\\', '/') + "/" + archivedFileName + ".tar";
/* 536 */         decompressedFile = new File(archivedFilePath);
/*     */ 
/* 539 */         compress = new BZip2Compress();
/* 540 */         decompressedResult = compress.decompress(compressedFile, decompressedFile);
/*     */ 
/* 543 */         if (decompressedResult.exists()) {
/* 544 */           archive = new TarArchive();
/* 545 */           resultList = archive.unarchive(decompressedFile, outputFile, encodingType);
}
/* 547 */         throw new IllegalStateException("The decompressed file cannot be found.");
/*     */       }
/*     */       finally {
/* 550 */         if ((decompressedResult != null) && (decompressedResult.exists()))
/* 551 */           decompressedResult.delete();
/*     */       }

/*     */     }
/*     */ 
/* 555 */     return resultList;
/*     */   }

    /*     */
    /*     */ protected static File createNewFile(File wishFile)
    /*     */ {
        /* 566 */ String timestamp = DateUtil.getDate("yyyyMMddHHmmssSSS");
        /* 567 */ String compressedFilePath = wishFile.getAbsolutePath();
        /*     */
        /* 569 */ String filename = null;
        /* 570 */ String extention = null;
        /*     */
        /* 572 */ if (compressedFilePath.endsWith(".tar.gz")) {
            /* 573 */ filename = compressedFilePath.substring(0, compressedFilePath.lastIndexOf(".tar.gz"));
            /* 574 */ extention = ".tar.gz";
            /* 575 */ } else if (compressedFilePath.endsWith(".tar.bz2")) {
            /* 576 */ filename = compressedFilePath.substring(0, compressedFilePath.lastIndexOf(".tar.bz2"));
            /* 577 */ extention = ".tar.bz2";
            /*     */ } else {
            /* 579 */ filename = FileUtil.getFileNameWithoutExtension(compressedFilePath);
            /* 580 */ extention = FileUtil.getExtension(compressedFilePath);
            /*     */ }
        /*     */
        /* 583 */ File resultFile = new File(filename + "[" + timestamp + "]." + extention);
        /*     */
        /* 585 */ while (resultFile.exists()) {
            /* 586 */ resultFile = new File(filename + "[" + timestamp + "]." + extention);
            /*     */ }
        /* 588 */ return resultFile;
        /*     */ }
    /*     */ }
