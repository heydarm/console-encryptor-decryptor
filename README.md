Console encryptor decryptor has the folowing arguments which should be passed:  
**`-mode`** — could be "enc" or "dec" which means encoding or decoding respectively.  
**`-key`**  — integer which is used to en/decrypt data.  
**`-data`** — string which should be en/decrypted.  
**`-in`**   — path to source file. If set, takes a line from the file and en/decrypts it.  
**`-out`**  — path to destination file. If set, writes the en/decrypted text to a file. Otherwise output it in command line.  
**`-alg`**  — algorithm. There are already 2 algorithms: "unicode" and "shift".  
