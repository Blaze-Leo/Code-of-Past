
---

# README (Decryption Routine)

This document explains how John (the receiver) can decrypt and verify the message sent by Anurag (the sender) using the provided OpenSSL-based RSA-KEM + AES-256 scheme.

---

## Files needed to Decrypt

| File                   | Purpose                                                                                      | Public / Private |
| ---------------------- | -------------------------------------------------------------------------------------------- |------------------|
| `k.enc`                | RSA-encrypted (encapsulated) secret key. Encrypted with your public key (`john_public.pem`). | Public           |
| `iv.bin`               | Initialization vector (IV) used for AES-256-CBC encryption.                                  | Public           |
| `anurag_gupta.txt.enc` | Ciphertext — the AES-256-CBC encrypted message.                                              | Public           |
| `signature.bin`        | Digital signature over the ciphertext, created using sender’s private key.                   | Public           |
| `anurag_public.pem`    | Sender’s public RSA key (used to verify the digital signature).                              | Public           |
| `john_private.pem`     | Your private RSA key (used to decrypt `k.enc`).                                              | Private          |

---

## How to Decrypt and Verify

Run this receiver script provided as commands in the terminal.

```bash
openssl pkeyutl -decrypt -inkey john_private.pem -in k.enc -out k.bin -pkeyopt rsa_padding_mode:oaep -pkeyopt rsa_oaep_md:sha256
openssl dgst -sha256 -binary k.bin > key.bin
KEYHEX=$(xxd -p key.bin | tr -d '\n')
IVHEX=$(xxd -p iv.bin | tr -d '\n')
openssl enc -d -aes-256-cbc -in anurag_gupta.txt.enc -out anurag_gupta.txt -K $KEYHEX -iv $IVHEX
openssl dgst -sha1 -verify anurag_public.pem -signature signature.bin anurag_gupta.txt.enc
```

## Command Explanation

Decrypt the encapsulated key `k.enc` using your RSA private key
Compute the AES key as the SHA-256 hash of `k`
Extract the AES key and IV in hexadecimal form
Use the derived AES-256 key and IV to decrypt the message
Check the signature of the ciphertext using sender's public key

Expected output: Verified OK
