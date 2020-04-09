#!/bin/bash
rsync -aSvH --delete /backup/* /
# restorecon -Rv /
