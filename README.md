# File-System-Analyzer
File System Analyzer for CS 350 Operating Systems Course. 
The source code check directories recursively. 
When the code executed, It stores every single file extension in a “Hash Map” structure with total count of them. 
Also, there are some Array Lists which stores total file count and total file size in a specific size ranges like “0KB <= File Size < 50KB”. 
Finally, It write these values in the Map and Lists to an excel file which you can create in a specific location with a specific name on your computer thanks to POI external jar file.

In the excel file, there are 2 tables. The first one stores file extensions and how many do we have in our computer. Second table stores size ranges, total count, total KB, overall ratio and penetration in that specific range.
