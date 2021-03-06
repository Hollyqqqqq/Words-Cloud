package project;

    /* ***** BEGIN LICENSE BLOCK *****
     * Version: MPL 1.1/GPL 2.0/LGPL 2.1
     *
     * The contents of this file are subject to the Mozilla Public License Version
     * 1.1 (the "License"); you may not use this file except in compliance with
     * the License. You may obtain a copy of the License at
     * http://www.mozilla.org/MPL/
     *
     * Software distributed under the License is distributed on an "AS IS" basis,
     * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
     * for the specific language governing rights and limitations under the
     * License.
     *
     * The Original Code is mozilla.org code.
     *
     * The Initial Developer of the Original Code is
     * Netscape Communications Corporation.
     * Portions created by the Initial Developer are Copyright (C) 1998
     * the Initial Developer. All Rights Reserved.
     *
     * Alternatively, the contents of this file may be used under the terms of
     * either of the GNU General Public License Version 2 or later (the "GPL"),
     * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
     * in which case the provisions of the GPL or the LGPL are applicable instead
     * of those above. If you wish to allow use of your version of this file only
     * under the terms of either the GPL or the LGPL, and not to allow others to
     * use your version of this file under the terms of the MPL, indicate your
     * decision by deleting the provisions above and replace them with the notice
     * and other provisions required by the GPL or the LGPL. If you do not delete
     * the provisions above, a recipient may use your version of this file under
     * the terms of any one of the MPL, the GPL or the LGPL.
     *
     * ***** END LICENSE BLOCK ***** */

    /*
     * DO NOT EDIT THIS DOCUMENT MANUALLY !!!
     * THIS FILE IS AUTOMATICALLY GENERATED BY THE TOOLS UNDER
     *    AutoDetect/tools/
     */

// package org.mozilla.intl.chardet
// needs charset.jar

import java.io.* ;
import java.net.* ;
import java.util.* ;
import org.mozilla.intl.chardet.* ;

public class FileCharsetDetector {

        private static boolean found = false ;
        private static String charsetName;


        public FileCharsetDetector(){
        }
        //我把这个方法改了一下，可以直接调用这个方法得到编码格式
        public String charSetDetect (String path) {

            // Initialize the nsDetector() ;
            int lang = nsPSMDetector.ALL ;
            nsDetector det = new nsDetector( lang) ;

            // Set an observer...
            // The Notify() will be called when a matching charset is found.

            det.Init( new nsICharsetDetectionObserver() {
                public void Notify(String charset) {
                    FileCharsetDetector.found = true ;
                    charsetName = charset;
                }
            });

            URL url = null;
            BufferedInputStream imp = null;
            try {
                url = new File( path).toURI().toURL();
                imp = new BufferedInputStream( url.openStream());
                byte[] buf = new byte[1024] ;
                int len;
                boolean done = false ;
                boolean isAscii = true ;

                while ( (len=imp.read(buf,0,buf.length)) != -1) {
                    // Check if the stream is only ascii.
                    if (isAscii)
                        isAscii = det.isAscii( buf, len);

                    // DoIt if non-ascii and not done yet.
                    if (!isAscii && !done)
                        done = det.DoIt( buf, len, false);
                }
                det.DataEnd();
                imp.close();

                if (isAscii) {
                    System.out.print( "CHARSET = ASCII");
                    tryToOutput( path, "ASCII" );
                    found = true ;
                }

                if (!found) {
                    String prob[] = det.getProbableCharsets() ;
                    for (int i = 0; i < prob.length; i++) {
                        System.out.print( "Probable Charset = " + prob[i] );
                        tryToOutput( path, prob[i] );
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return charsetName;
        }

        private static void tryToOutput (String path, String charSetName) {
            InputStream inputStream = null;
            BufferedReader in = null;
            Scanner ins = null;
            try {
                inputStream = new FileInputStream( path);
                in = new BufferedReader( new InputStreamReader( inputStream, charSetName ));
                ins = new Scanner( in );
                String s = ins.next();
                System.out.println ( " : " + s );
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ins.close();
            }
        }

    public static class ReadMe {
        /**
         FileCharsetDetector is designed by MX He
         that help to detect possible CharsetNames or Encodings of a text file.

         It contains only one class FileCharsetDetector.java
         and a lib chardet.jar from:
         https://sourceforge.net/projects/jchardet/

         The Usage :
         java FileCharsetDetector <path>

         The running results:

         ..>java FileCharsetDetector ./test1_utf8_noBOM.txt
         Probable Charset = UTF-8 : 中文测试
         Probable Charset = Shift_JIS : 荳?譁?豬玖??
         Probable Charset = GB18030 : 涓枃娴嬭瘯
         Probable Charset = UTF-16LE : ?螖??閯
         Probable Charset = windows-1252 : ????–????è??
         Probable Charset = UTF-16BE : ?隇诨?

         ..>java FileCharsetDetector ./test2_Default.txt
         Probable Charset = UTF-8 : ?中文测试
         Probable Charset = UTF-16LE : ??雦讵??
         Probable Charset = GB18030 : 锘夸腑鏂囨祴璇?
         Probable Charset = UTF-16BE : ???蟦???
         Probable Charset = windows-1252 : ???????–????è??

         ..>java FileCharsetDetector ./test3_utf8.txt
         Probable Charset = UTF-8 : 中文测试
         Probable Charset = Shift_JIS : 荳?譁?豬玖??
         Probable Charset = GB18030 : 涓枃娴嬭瘯
         Probable Charset = UTF-16LE : ?螖??閯
         Probable Charset = windows-1252 : ????–????è??
         Probable Charset = UTF-16BE : ?隇诨?

         ..>java FileCharsetDetector ./test4_ASCII_Escap.txt
         CHARSET = ASCII : \u4E2D\u6587\u6D4B\u8BD5

         ..>java FileCharsetDetector ./test5_ASCII.txt
         Probable Charset = GB18030 : 中文测试
         Probable Charset = Shift_JIS : ?????簗?
         Probable Charset = EUC-JP : 嶄猟霞編
         Probable Charset = UTF-16LE : ???
         Probable Charset = EUC-KR : 櫓匡?桿
         Probable Charset = UTF-16BE : ????
         Probable Charset = Big5 : 笢恅聆彸
         Probable Charset = GB2312 : 中文测试

         */

    }
}


