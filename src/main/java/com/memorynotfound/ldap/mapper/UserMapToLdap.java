package com.memorynotfound.ldap.mapper;

import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.memorynotfound.ldap.model.LdapUser;
import com.memorynotfound.ldap.model.User_DB;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class UserMapToLdap {

    private UserMapToLdap() {}


    public static User_DB mapToUserDB(LdapUser ldapUser) {

            return User_DB.builder()
                    .firstName(ldapUser.getFirsName())
                    .lastName(ldapUser.getLastName())
                    .eMailAddress(ldapUser.getEmail())
                    .login(ldapUser.getLogin())
                    .keycloakId(UUID.nameUUIDFromBytes(ldapUser.getKeycloakId().getBytes()).toString())
                    .password("123456")
                    .isArchive(false)
                    .organization(1L)
                    .build();

//                    .keycloakId(
    //                        UUID.nameUUIDFromBytes(ldapUser.getKeycloakId().getBytes(StandardCharsets.UTF_16)).toString()
//                            UUID.nameUUIDFromBytes(ldapUser.getKeycloakId().getBytes()).toString()
//                            UUID.nameUUIDFromBytes(ldapUser.getKeycloakId().getBytes()).toString()
//
//                    )

    }

//SearchResultEntry: member.setObjectGUID(Utiles.bytesToUUID(result.getAttribute(attribute).getValueByteArray()).toString());


    private static String convertObjectGUID(Object objectGUID) {
        byte[] guid = toByteArray(objectGUID);

        String strGUID = "";
        AtomicReference<StringBuilder> byteGUID = new AtomicReference<>(new StringBuilder());


        for (byte b : guid) {
            byteGUID.get().append("\\").append(addLeadingZero(b & 0xFF));
        }

        strGUID = strGUID + addLeadingZero(guid[3] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[2] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[1] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[0] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero(guid[5] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[4] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero(guid[7] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[6] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero(guid[8] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[9] & 0xFF);
        strGUID = strGUID + "-";
        strGUID = strGUID + addLeadingZero(guid[10] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[11] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[12] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[13] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[14] & 0xFF);
        strGUID = strGUID + addLeadingZero(guid[15] & 0xFF);

        return strGUID;
    }

    private static String addLeadingZero(int k) {
        return (k <= 0xF) ? "0" + Integer.toHexString(k) : Integer.toHexString(k);
    }

    private static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
