#!/bin/bash
rsync -aSvH /* /backup --delete --exclude=/backup --exclude=/sys --exclude=/tmp --exclude=/proc --exclude=/mnt --exclude=/media --exclude=/run

